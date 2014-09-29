package com.kotikan.hack.picture.heartbeat;

import com.kotikan.hack.picture.imageServer.Images;
import com.kotikan.hack.picture.model.Session;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class Beats {

    public static HeartBeats instance() {

        return new HeartBeats() {
            @Override
            public BeatInfo pulse(Session session) {
                if (Images.instance().isNewImageRequired(session)) {
                    return BeatInfo.CHANGED;
                } else {
                    return BeatInfo.UNCHANGED;
                }
            }
        };
    }

}
