package com.kotikan.hack.picture.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roberthewitt on 30/09/2014.
 */
public class HostedImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getOutputStream().println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Photosplitter</title>\n" +
                "    <script type=\"text/javascript\" src=\"/js/jquery-1.11.1.js\"></script>\n" +
                "    <script type=\"text/javascript\" src=\"/js/image.js\"></script>\n" +
                "    <link type=\"text/css\" rel=\"stylesheet\" href=\"/bootstrap/css/bootstrap.css\">\n" +
                "    <link type=\"text/css\" rel=\"stylesheet\" href=\"/bootstrap/css/bootstrap-responsive.css\">\n" +
                "    <link type=\"text/css\" rel=\"stylesheet\" href=\"/css/style.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"container\">\n" +
                "\n" +
                "    <div id=\"outputLog\"></div>\n" +
                "\n" +
                "    <div>\n" +
                "        <img id=\"myImage\"/>\n" +
                "    </div>\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "        function init() {\n" +
                "            google.devrel.samples.hello.init('//' + window.location.host + '/_ah/api');\n" +
                "        }\n" +
                "    </script>\n" +
                "    <script src=\"https://apis.google.com/js/client.js?onload=init\"></script>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n");
    }
}
