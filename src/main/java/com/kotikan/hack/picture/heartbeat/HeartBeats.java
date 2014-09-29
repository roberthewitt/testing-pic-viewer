package com.kotikan.hack.picture.heartbeat;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public interface HeartBeats {

    void register(String session, String height, String width, String imgRequested);
}
