package com.kotikan.hack.picture.imageServer;

import com.kotikan.hack.picture.heartbeat.HeartBeats;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public interface RedirectGenerator {

    String getRedirectUrl(HeartBeats registration);

}
