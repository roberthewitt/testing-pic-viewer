package com.kotikan.hack.picture.imageServer;

import com.google.appengine.api.images.Image;
import com.kotikan.hack.picture.model.Session;

import java.util.Map;

/**
 * Created by roberthewitt on 30/09/2014.
 */
public class ImageCropResult {

    public static final Data NO_DATA = new Data(null, null);

    private Map<Session, Data> dataMap;

    public ImageCropResult(Map<Session, Data> dataMap) {
        this.dataMap = dataMap;
    }

    public String getUrlFor(Session session) {
        String result = "sad face";
        Data data = dataMap.get(session);
        if (data != null) {
            result = data.url;
        }
        return result;
    }

    public Image getDataForHostedKey(String hostedKey) {
        for (Session key : dataMap.keySet()) {
            Data data = dataMap.get(key);
            if (data.url.contains(hostedKey)) {
                return data.image;
            }
        }
        return null;
    }
}
