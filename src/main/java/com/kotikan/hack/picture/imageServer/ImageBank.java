package com.kotikan.hack.picture.imageServer;

import com.kotikan.hack.picture.model.Session;

import java.util.Set;

/**
 * Created by roberthewitt on 30/09/2014.
 */
public interface ImageBank {
    ImageCropResult cropImageFor(String urlForSession, Set<Session> sessions);
}
