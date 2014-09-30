package com.kotikan.hack.picture.imageServer;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.kotikan.hack.picture.model.Session;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by roberthewitt on 30/09/2014.
 */
class MemoryCachedImageBank implements ImageBank {

    private final ImageCropResult NO_DATA = new ImageCropResult(new HashMap<Session, Data>());

    final Map<String, String> hostedEndPointsToActualRemoteImages = new HashMap<>();
    final UrlGenerator croppedGenerator = new UniqueImageUrlGenerator("cropped");
    final UrlGenerator hostedGenerator = new UniqueImageUrlGenerator("hostedImage");

    @Override
    public Image getImageFor(Session session) {
        for (String url : remoteUrlsToImageData.keySet()) {
            ImageCropResult imageCropResult = remoteUrlsToImageData.get(url);
            Data data = imageCropResult.getDataForFor(session);
            if (data != ImageCropResult.NO_DATA) {
                return data.image;
            }
        }
        return null;
    }

    @Override
    public String registerImageRequest(String url) {
        System.out.println("MemoryCachedImageBank.registerImageRequest");
        String k = hostedGenerator.newUrl();
        hostedEndPointsToActualRemoteImages.put(k, url);
        System.out.println(String.format("k, v = (%s, %s)", k, url));
        return k;
    }

    private static final Map<String, ImageCropResult> remoteUrlsToImageData = new HashMap<>();

    @Override
    public ImageCropResult cropImageFor(String urlForSession, Set<Session> sessions) {
        System.out.println("MemoryCachedImageBank.cropImageFor");
        System.out.println("urlForSession = " + urlForSession);
        ImageCropResult imageCropResult = NO_DATA;
        try {

            final byte[] originalUrlData = URLFetchServiceFactory.getURLFetchService().fetch(new URL(urlForSession)).getContent();

            final ImagesService imagesService = ImagesServiceFactory.getImagesService();
            final Image originalImage = ImagesServiceFactory.makeImage(originalUrlData);

            if (originalUrlData != null) {

                final Map<Session, Data> dataMap = new HashMap<>();
                Iterator<Session> sessionIterator = sessions.iterator();

                if (sessions.size() == 1) {
                    System.out.println("generating a single image");

                    Session onlySession = sessionIterator.next();

                    Transform resize = ImagesServiceFactory.makeResize(onlySession.width, onlySession.height);
                    Image newImage = imagesService.applyTransform(resize, originalImage);

                    String url = croppedGenerator.newUrl();
                    dataMap.put(onlySession, new Data(url, newImage));

                } else if (sessions.size() == 2) {
                    System.out.println("generating a double image");
                    Session first = sessionIterator.next();
                    Session second = sessionIterator.next();

                    int minimumHeight = Math.min(first.height, second.height);
                    Transform resize = ImagesServiceFactory.makeResize(first.width + second.width, minimumHeight);
                    Image scaledImage = imagesService.applyTransform(resize, originalImage);

                    Transform transform1 = ImagesServiceFactory.makeCrop(0, 0, first.width, minimumHeight);
                    Image image1 = imagesService.applyTransform(transform1, scaledImage);
                    dataMap.put(first, new Data(croppedGenerator.newUrl(), image1));

                    Transform transform2 = ImagesServiceFactory.makeCrop(0, 0, second.width, minimumHeight);
                    Image image2 = imagesService.applyTransform(transform2, scaledImage);
                    dataMap.put(second, new Data(croppedGenerator.newUrl(), image2));
                }

                imageCropResult = new ImageCropResult(dataMap);
                remoteUrlsToImageData.put(urlForSession, imageCropResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageCropResult;
    }
}
