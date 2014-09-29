package com.kotikan.hack.picture.servlets;

import com.kotikan.hack.picture.model.Session;
import com.kotikan.hack.picture.registration.DeviceRegistration;
import com.kotikan.hack.picture.registration.Devices;
import com.kotikan.hack.picture.registration.JsonResponse;
import com.kotikan.hack.picture.registration.SessionResponseBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session session = Devices.instance().registerForSession(req);
        SessionResponseBuilder builder = new JsonResponse();

        resp.getOutputStream().write(builder.forSession(session).getBytes());

    }

}
