package com.kotikan.hack.picture.registration;

import com.kotikan.hack.picture.model.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class Devices {

    private final static Map<String, Set<Session>> urlSessionMap = new HashMap<>();
    private final static Map<Session, String> sessionUrlMap = new HashMap<>();

    public static DeviceRegistration instance() {
        return new DeviceRegistration() {
            @Override
            public Session registerForSession(HttpServletRequest req) {
                int deviceHeight = paramInt(req, "h");
                int deviceWidth = paramInt(req, "w");
                //        return "http://banded-arcana-718.appspot.com/static_resource/tree.jpg";
                String url = "http://localhost:8080/static_resource/tree.jpg";
                final Session session = Session.newSession(deviceHeight, deviceWidth);

                sessionUrlMap.put(session, url);

                Set<Session> sessions = urlSessionMap.get(url);
                if (sessions == null) {
                    sessions = new HashSet<>();
                    urlSessionMap.put(url, sessions);
                }
                sessions.add(session);

                return session;
            }

            @Override
            public Session fromId(HttpServletRequest req) {
                Session session = null;
                String sessionId = paramString(req, "sessionId");
                for (Session s : sessionUrlMap.keySet()) {
                    if (s.sessionId.equals(sessionId)) {
                        session = s;
                        break;
                    }
                }
                return session;
            }

            @Override
            public Set<Session> sessionsForUrl(String url) {
                Set<Session> sessions = urlSessionMap.get(url);
                if (sessions == null) {
                    sessions = new HashSet<>();
                }
                return sessions;
            }

            @Override
            public String urlForSession(Session session) {
                return sessionUrlMap.get(session);
            }

            private int paramInt(HttpServletRequest req, String dimension) {
                int param = 0;
                String value = req.getParameter(dimension);
                if (value != null) {
                    param = Integer.valueOf(value);
                }
                return param;
            }

            private String paramString(HttpServletRequest req, String dimension) {
                String param = null;
                String value = req.getParameter(dimension);
                if (value != null) {
                    param = value;
                }
                return param;
            }
        };
    }

}
