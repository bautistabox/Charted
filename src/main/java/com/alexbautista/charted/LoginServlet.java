package com.alexbautista.charted;

import com.alexbautista.charted.model.ConnectionFactory;
import com.alexbautista.charted.model.ConnectionFactoryImpl;
import com.alexbautista.charted.model.DigestMessage;
import com.alexbautista.charted.model.DigestMessageImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        name="login_servlet",
        urlPatterns = "/loginServlet"
)

public class LoginServlet extends HttpServlet {
    private final ConnectionFactory connectionFactory = new ConnectionFactoryImpl();

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        boolean status;

        DigestMessage digestMessage = new DigestMessageImpl();

        String user = req.getParameter("username");
        String pass = digestMessage.getDigest(req.getParameter("userpass"));
        int userId;
        try {
            try (Connection conn = connectionFactory.getConnection()) {
                try (PreparedStatement ps = conn.prepareStatement("select * from user where email=? and pass=?")) {
                    ps.setString(1, user);
                    ps.setString(2, pass);
                    try (ResultSet rs = ps.executeQuery()) {
                        status = rs.next();
                        userId = rs.getInt("id");
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        if(status) {
            HttpSession session = req.getSession();
            session.setAttribute("user", "admin");
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("user", user);
            Cookie uID = new Cookie("uid", String.valueOf(userId));
            userName.setMaxAge(30*60);
            resp.addCookie(userName);
            resp.addCookie(uID);
            resp.sendRedirect("home.jsp");
        } else {
            out.print("Sorry username or password is incorrect");
            //RequestDispatcher rd = req.getRequestDispatcher("index.js");
            //rd.include(req, resp);
        }
        out.close();
    }
}
