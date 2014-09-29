package com.kotikan.hack.picture.heartbeat;

import com.kotikan.hack.picture.model.Session;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public interface HeartBeats {

    BeatInfo pulse(Session session);
}
