package com.kotikan.hack.picture.servlets;

import com.kotikan.hack.picture.imageServer.Images;
import com.kotikan.hack.picture.model.Session;
import com.kotikan.hack.picture.registration.Devices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roberthewitt on 30/09/2014.
 */
public class CroppedImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        final Session session = Devices.instance().fromId(req);
        if (session == null) {
            resp.setStatus(404);
            return;
        }
        String cachedImageUrl = requestURI.replace("/cropped/", "");

        Images.bank().getImageFor(session, cachedImageUrl);


    }
}
