package com.alexbautista.charted;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/LogoutServlet")

public class logoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    System.out.println("JSESSIONID="+cookie.getValue());
                    break;
                }
            }
        }
        HttpSession session = req.getSession(false);
        System.out.println("User="+session.getAttribute("user"));
        if(session != null) {
            session.invalidate();
        }
        resp.sendRedirect("index.jsp");
    }
}
