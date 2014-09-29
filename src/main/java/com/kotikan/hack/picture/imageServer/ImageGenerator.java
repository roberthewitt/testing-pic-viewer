package com.kotikan.hack.picture.imageServer;

import com.kotikan.hack.picture.model.Session;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public interface ImageGenerator {

    String generateUrlFor(Session session);

    boolean isNewImageRequired(Session session);
}
