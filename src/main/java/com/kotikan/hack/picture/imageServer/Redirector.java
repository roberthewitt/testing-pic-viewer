package com.kotikan.hack.picture.imageServer;

import com.kotikan.hack.picture.heartbeat.HeartBeats;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class Redirector {

    public static RedirectGenerator instance() {
        return new RedirectGenerator() {
            @Override
            public String getRedirectUrl(HeartBeats registration) {
                return "http://images4.fanpop.com/image/photos/14700000/Beautifull-cat-cats-14749885-1600-1200.jpg";
            }
        };
    }

}
