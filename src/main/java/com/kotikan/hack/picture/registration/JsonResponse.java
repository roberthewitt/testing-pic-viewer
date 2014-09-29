package com.kotikan.hack.picture.registration;

import com.kotikan.hack.picture.model.Session;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class JsonResponse implements SessionResponseBuilder {
    @Override
    public String forSession(Session session) {
        final String newLine = "\n";
        return "{ " + newLine +
                    "\"sessionId\":\"" + session.sessionId + "\"" + newLine +
                    "\"device_height\": " + session.height + newLine +
                    "\"device_width\": " + session.width + newLine +
                "}";
    }
}
