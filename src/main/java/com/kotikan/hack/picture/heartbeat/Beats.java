package com.kotikan.hack.picture.heartbeat;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class Beats {

    public static HeartBeats instance() {
        return new HeartBeats() {
            @Override
            public void register(String session, String height, String width, String imgRequested) {

            }
        };
    }

}
