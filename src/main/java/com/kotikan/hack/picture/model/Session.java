package com.kotikan.hack.picture.model;

import java.util.UUID;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class Session {

    public final String sessionId;
    public final int height;
    public final int width;

    public static Session newSession() {
        return new Session(UUID.randomUUID().toString(), 800, 600);
    }

    private Session(String sessionId, int height, int width) {
        this.sessionId = sessionId;
        this.height = height;
        this.width = width;
    }
}
