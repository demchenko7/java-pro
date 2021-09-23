package com.example.anketa;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(name = "Anketa", value = "/anketa")
public class Anketa extends HttpServlet {
    private String message;
    private AtomicInteger countContact1 = new AtomicInteger(0);
    private AtomicInteger countContact2 = new AtomicInteger(0);
    private AtomicInteger countContact3 = new AtomicInteger(0);

    private AtomicInteger count2Contact1 = new AtomicInteger(0);
    private AtomicInteger count2Contact2 = new AtomicInteger(0);
    private AtomicInteger count2Contact3 = new AtomicInteger(0);

    public void init() {
        message = "АНКЕТА";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String contact = request.getParameter("contact");


        String msg = "";
        String title = "";
        int result1 = 0;
        int result2 = 0;
        if(contact != null) {
            result1 = 0;
            if (contact.equals("1")) {
                countContact1.incrementAndGet();
            } else if (contact.equals("2")) {
                countContact2.incrementAndGet();
            } else if (contact.equals("3")) {
                countContact3.incrementAndGet();
            }
        }
        result1 = countContact1.get() + countContact2.get() + countContact3.get();

        String contact2 = request.getParameter("contact2");
        if(contact2 != null) {
            result2 = 0;
            if (contact2.equals("1")) {
                count2Contact1.incrementAndGet();
            } else if (contact2.equals("2")) {
                count2Contact2.incrementAndGet();
            } else if (contact2.equals("3")) {
                count2Contact3.incrementAndGet() ;
            }
        }
        result2 = count2Contact1.get() + count2Contact2.get() + count2Contact3.get();

        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("stat.jsp");
        rd.include(request, response);

        int count = 0;

        PrintWriter out = response.getWriter();
        out.println("<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Result</title>\n" +
                "</head><body>");
        out.println("<h3> " +
                "По первому вопросу проголосовали " + result1 + getChel(result1) +
                "</h3>");
        count = countContact1.get();
        out.println("<p> Выбрали 'Деньги' " + count + getChel(count) + "</p>");
        count = countContact2.get();
        out.println("<p> Выбрали 'Знание' " + count + getChel(count) + "</p>");
        count = countContact3.get();
        out.println("<p> Выбрали 'Духовность' " + count + getChel(count) + "</p>");
        out.println("<h3> " +
                "По второму вопросу проголосовали " + result2 + getChel(result2) +
                "</h3>");
        count = count2Contact1.get();
        out.println("<p> Верующие -  " + count + getChel(count) + "</p>");
        count = count2Contact2.get();
        out.println("<p> Атеисты -  " + count + getChel(count) + "</p>");
        count = count2Contact3.get();
        out.println("<p> Не смогли ответить -  " + count + getChel(count) + "</p>");
        out.println("</body></html>");
    }

    private String getChel(int count){
        String str = Integer.toString(count);
        if(str.endsWith("2") || str.endsWith("3") || str.endsWith("4"))
           return " человека.";
        else
            return " человек.";
    }

    public void destroy() {
    }
}

