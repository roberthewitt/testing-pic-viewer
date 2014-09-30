package com.kotikan.hack.picture.imageServer;

import com.kotikan.hack.picture.Constants;
import com.kotikan.hack.picture.model.Session;

import java.util.Map;

/**
 * Created by roberthewitt on 30/09/2014.
 */
public class ImageCropResult {

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
}
