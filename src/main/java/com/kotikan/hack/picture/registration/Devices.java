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
                String url = generateRandomCatUrl();
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

    private static String generateRandomCatUrl() {
        String[] cats = {
                "http://stylonica.com/wp-content/uploads/2014/03/cats-16140154-1920-1080.jpg"
                ,"http://images4.fanpop.com/image/photos/14700000/Beautifull-cat-cats-14749885-1600-1200.jpg"
                ,"http://jasonlefkowitz.net/wp-content/uploads/2013/07/Cute-Cats-cats-33440930-1280-800.jpg"
                ,"http://stylonica.com/wp-content/uploads/2014/03/cats-cats-22066039-1280-1024.jpg"
                ,"http://desktopwallpapers.biz/wp-content/uploads/2014/09/Cats-HD.jpg"
                ,"http://sociorocketnewsen.files.wordpress.com/2014/04/ci-9.png?w=580&h=411"
                ,"http://www.chicagonow.com/steve-dales-pet-world/files/2013/10/Nyan-Nayan-Wine-for-cats-Steve-Dale-Animal-news-624x439.jpg"
                ,"http://images4.fanpop.com/image/photos/16800000/Dog-and-Cat-Wallpaper-teddybear64-16834786-1280-800.jpg"
                ,"http://i.kinja-img.com/gawker-media/image/upload/s--Pn3NjGa5--/c_fit,fl_progressive,q_80,w_636/19fn7mgw2wtulpng.png"
        };
        return cats[randInt(0,cats.length -1)];
    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt((max - min) + 1) + min;
    }
}
