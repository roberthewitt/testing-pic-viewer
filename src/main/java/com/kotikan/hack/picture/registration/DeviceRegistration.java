package com.kotikan.hack.picture.registration;

import com.kotikan.hack.picture.model.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public interface DeviceRegistration {

    /**
     * localhost:8080/reg?h=555&w=444
     * <p/>
     * this will parse the h and w values from the url post
     *
     * @param req postRequest
     * @return a new Session object for this device
     */
    Session registerForSession(HttpServletRequest req);

    Session fromId(HttpServletRequest req);

    String urlForSession(Session session);

    Set<Session> sessionsForUrl(String url);
}
