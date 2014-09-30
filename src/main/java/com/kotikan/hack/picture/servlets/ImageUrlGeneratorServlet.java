package com.kotikan.hack.picture.servlets;

import com.kotikan.hack.picture.heartbeat.BeatInfo;
import com.kotikan.hack.picture.imageServer.Images;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roberthewitt on 30/09/2014.
 */
public class ImageUrlGeneratorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedUrl = paramString(req, "url");
        String resultingUrl = Images.bank().registerImageRequest(requestedUrl);

        String newLine = "\n";
        String jsonBody = "{ " + newLine;
        jsonBody +=  "\"url\": " + resultingUrl + newLine;
        jsonBody += "}";
        resp.getWriter().write(jsonBody);
    }

    private String paramString(HttpServletRequest req, String dimension) {
        String param = null;
        String value = req.getParameter(dimension);
        if (value != null) {
            param = value;
        }
        return param;
    }
}
