package com.kotikan.hack.picture.imageServer;

import com.kotikan.hack.picture.model.Session;

/**
 * Created by roberthewitt on 30/09/2014.
 */
public class ImageCropResult {

    public String getUrlFor(Session session) {
        return "http://localhost:8080/static_resource/tree.jpg";
    }
}
