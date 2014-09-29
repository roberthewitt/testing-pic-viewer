package com.kotikan.hack.picture.servlets;

import com.kotikan.hack.picture.heartbeat.BeatInfo;
import com.kotikan.hack.picture.heartbeat.Beats;
import com.kotikan.hack.picture.imageServer.Images;
import com.kotikan.hack.picture.model.Session;
import com.kotikan.hack.picture.registration.Devices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roberthewitt on 29/09/2014.
 */
public class HeartBeatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Session session = Devices.instance().fromId(req);
        if (session == null) {
            resp.setStatus(404);
            return;
        }

        final BeatInfo result = Beats.instance().pulse(session);

        String newLine = "\n";
        boolean isChanged = BeatInfo.CHANGED == result;
        String jsonBody = "{ " + newLine +
                "\"isChanged\": " + isChanged + newLine;

        if (isChanged) {
            String url = Images.instance().generateUrlFor(session);
            jsonBody += "\"url\":\"" + url + "\"" + newLine;
        }

        jsonBody += "}";
        resp.getWriter().write(jsonBody);
    }
}
