package com.kotikan.hack.picture.imageServer;

import com.kotikan.hack.picture.Constants;

import java.util.UUID;

/**
 * Created by roberthewitt on 30/09/2014.
 */
class UniqueImageUrlGenerator implements UrlGenerator {

    private final String path;

    public UniqueImageUrlGenerator(String path) {
        this.path = path;
    }

    @Override
    public String newUrl() {
        return Constants.HOST + "/" + path + "/" + UUID.randomUUID().toString();
    }
}
