package com.kotikan.hack.picture.servlets;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServicePb;
import com.google.appengine.repackaged.com.google.common.util.Base64;
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

        final String hostedKey = requestURI.replace("/cropped/", "");
        System.out.println("hostedKey = " + hostedKey);

        Image image = Images.bank().getImageForHostedKey(hostedKey);
        System.out.println("image = " + image);
        if (image != null) {
            System.out.println("image.getFormat() = " + image.getFormat());
            resp.setContentType("image/png");
            resp.getOutputStream().write(image.getImageData());
        }
    }
}
