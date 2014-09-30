package com.kotikan.hack.picture.imageServer;

import com.kotikan.hack.picture.model.Session;
import com.kotikan.hack.picture.registration.DeviceRegistration;
import com.kotikan.hack.picture.registration.Devices;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by roberthewitt on 29/09/2014.
 */
public class Images {

    private static final Map<Session, LastKnownData> cache = new HashMap<>();

    public static ImageGenerator instance() {
        return new ImageGenerator() {
            @Override
            public String generateUrlFor(Session session) {
                DeviceRegistration deviceRegistration = Devices.instance();
                String remoteImageValue = null;
                final String hostedImageKey = deviceRegistration.urlForSession(session);

                for (String hosted : imageBank.hostedEndPointsToActualRemoteImages.keySet()) {

                    System.out.println(String.format("hostedShort = %s ; hostedFull = %s", hostedImageKey, hosted));
                    if (hosted.contains(hostedImageKey)) {
                        remoteImageValue = imageBank.hostedEndPointsToActualRemoteImages.get(hosted);
                        System.out.println("remoteImageValue = " + remoteImageValue);
                        break;
                    }
                }
                ImageCropResult result = new ImageCropResult(new HashMap<Session, Data>());

                if (remoteImageValue != null) {
                    Set<Session> sessionSet = deviceRegistration.sessionsForUrl(hostedImageKey);
                    System.out.println("sessionSet = " + sessionSet.size());
                    result = Images.bank().cropImageFor(remoteImageValue, sessionSet);
                }

                return result.getUrlFor(session);
            }

            @Override
            public boolean isNewImageRequired(Session session) {
                LastKnownData lastKnownData = cache.get(session);
                if (lastKnownData == null) {
                    cache.put(session, new LastKnownData());
                    return true;
                }
                return false;
            }
        };
    }

    private static final MemoryCachedImageBank imageBank = new MemoryCachedImageBank();
    public static ImageBank bank() {
        return imageBank;
    }

    private static class LastKnownData {
    }
}
