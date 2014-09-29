package com.kotikan.hack.picture.imageServer;

import com.kotikan.hack.picture.model.Session;
import com.kotikan.hack.picture.registration.Devices;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class Images {

    private static final Map<Session, LastKnownData> cache = new HashMap<>();

    public static ImageGenerator instance() {
        return new ImageGenerator() {
            @Override
            public String generateUrlFor(Session session) {
                return Devices.instance().urlForSession(session);
            }

            @Override
            public boolean isNewImageRequired(Session session) {
                LastKnownData lastKnownData = cache.get(session);
                if (lastKnownData == null) {
                    cache.put(session, new LastKnownData());
                    return true;
                }
                return false;
            }
        };
    }

    private static class LastKnownData {
    }
}
