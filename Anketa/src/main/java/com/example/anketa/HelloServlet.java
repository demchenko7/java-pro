package com.example.anketa;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "HelloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "БУДЬТЕ СЧАСТЛИВЫ!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2><font color=#0000ff >" + message + "</h2>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}