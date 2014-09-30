package com.kotikan.hack.picture.imageServer;

import com.google.appengine.api.images.Image;

import java.awt.image.BufferedImage;

/**
* Created by roberthewitt on 30/09/2014.
*/
class Data {
    Image image;
    String url;

    public Data(String url, Image image) {
        this.image = image;
        this.url = url;
    }
}
