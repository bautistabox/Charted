package com.alexbautista.charted;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        name="login_servlet",
        urlPatterns = "/loginServlet"
)

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        boolean status;

        String user = req.getParameter("username");
        String pass = req.getParameter("userpass");

        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/charted_schema";
            Class.forName(myDriver);
            try (Connection conn = DriverManager.getConnection(myUrl, "root", "123!@#qweQWE")) {
                try (PreparedStatement ps = conn.prepareStatement("select * from user where email=? and pass=?")) {
                    ps.setString(1, user);
                    ps.setString(2, pass);
                    try (ResultSet rs = ps.executeQuery()) {
                        status = rs.next();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }

        if(status) {
            RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
            rd.forward(req, resp);
        } else {
            out.print("Sorry username or password is incorrect");
            RequestDispatcher rd = req.getRequestDispatcher("index.js");
            rd.include(req, resp);
        }
        out.close();
    }
}
