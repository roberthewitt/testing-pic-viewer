package com.kotikan.hack.picture.imageServer;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.repackaged.com.google.api.client.util.IOUtils;
import com.google.apphosting.datastore.DatastoreV4;
import com.google.apphosting.datastore.EntityV4;
import com.kotikan.hack.picture.model.Session;
import com.kotikan.hack.picture.registration.DeviceRegistration;
import com.kotikan.hack.picture.registration.Devices;

import javax.jdo.PersistenceManager;
import java.util.Date;
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

    public static ImageBank bank() {
        return new ImageBank() {
            @Override
            public ImageCropResult cropImageFor(String urlForSession, Set<Session> sessions) {
                return new ImageCropResult();
            }

            @Override
            public String registerImageRequest(String url) {
                return "http://localhost/hostedImage/tree";
            }
        };
    }

    private static class LastKnownData {
    }
}
