package com.kotikan.hack.picture.servlets;

import com.kotikan.hack.picture.heartbeat.Beats;
import com.kotikan.hack.picture.heartbeat.HeartBeats;
import com.kotikan.hack.picture.imageServer.RedirectGenerator;
import com.kotikan.hack.picture.imageServer.Redirector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HeartBeats registration = Beats.instance();
        RedirectGenerator server = Redirector.instance();

        String session = req.getParameter("");
        String height = req.getParameter("");
        String width = req.getParameter("");
        String imgRequested = req.getParameter("");
        registration.register(session, height, width, imgRequested);

        resp.sendRedirect(server.getRedirectUrl(registration));

    }

}
