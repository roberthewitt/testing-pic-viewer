package com.kotikan.hack.picture.imageServer;

import com.kotikan.hack.picture.model.Session;
import com.kotikan.hack.picture.registration.DeviceRegistration;
import com.kotikan.hack.picture.registration.Devices;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by roberthewitt on 29/09/2014.
 */
public class Images {

    private static final Map<Session, LastKnownData> cache = new HashMap<>();

    public static ImageGenerator instance() {
        return new ImageGenerator() {
            @Override
            public String generateUrlFor(Session session) {
                DeviceRegistration deviceRegistration = Devices.instance();
                final String urlForSession = deviceRegistration.urlForSession(session);

                Set<Session> sessionSet = deviceRegistration.sessionsForUrl(urlForSession);

                ImageBank bank = Images.bank();

                ImageCropResult result = bank.cropImageFor(urlForSession, sessionSet);

                return result.getUrlFor(session);
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

    private static final MemoryCachedImageBank imageBank = new MemoryCachedImageBank();
    public static ImageBank bank() {
        return imageBank;
    }

    private static class LastKnownData {
    }
}
