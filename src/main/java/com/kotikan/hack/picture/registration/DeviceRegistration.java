package com.kotikan.hack.picture.registration;

import com.kotikan.hack.picture.model.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public interface DeviceRegistration {

    Session registerForSession(HttpServletRequest req);
}
