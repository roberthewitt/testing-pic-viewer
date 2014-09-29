package com.kotikan.hack.picture.registration;

import com.kotikan.hack.picture.model.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class Devices {

    public static DeviceRegistration instance() {
        return new DeviceRegistration() {
            @Override
            public Session registerForSession(HttpServletRequest req) {
                int deviceHeight = getDeviceDimension(req, "h");
                int deviceWidth = getDeviceDimension(req, "w");
                return Session.newSession(deviceHeight, deviceWidth);
            }

            private int getDeviceDimension(HttpServletRequest req, String dimension) {
                int deviceHeight = 0;
                String h = req.getParameter(dimension);
                if (h != null) {
                    deviceHeight = Integer.valueOf(h);
                }
                return deviceHeight;
            }
        };
    }
}
