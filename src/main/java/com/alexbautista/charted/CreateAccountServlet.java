package com.alexbautista.charted;

import com.alexbautista.charted.model.ConnectionFactory;
import com.alexbautista.charted.model.ConnectionFactoryImpl;
import com.alexbautista.charted.model.PasswordHasher;
import com.alexbautista.charted.model.PasswordHasherImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        name = "create_account",
        urlPatterns = "/signup"
)

public class CreateAccountServlet extends HttpServlet {
    private final ConnectionFactory connectionFactory = new ConnectionFactoryImpl();
    private final PasswordHasher passwordHasher = new PasswordHasherImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        var username = req.getParameter("user");
        var pass1 = req.getParameter("pass1");
        var pass2 = req.getParameter("pass2");
        var e_pass = passwordHasher.hashPassword(pass1);

        if (pass1.equals(pass2)) {
            try {
                try (Connection conn = connectionFactory.getConnection()) {
                    // SQL INSERT query
                    var query = "INSERT into user (email, pass) VALUES (?, ?)";

                    // Java Statement
                    try (PreparedStatement ps = conn.prepareStatement(query)) {
                        ps.setString(1, username);
                        ps.setString(2, e_pass);
                        // execute the query and get java resultset
                        var result = ps.executeUpdate();
                        if (result == 1) {
                            req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
                        } else {
                            System.out.println("Creating Error");
                        }
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            PrintWriter out = resp.getWriter();
            out.print("Passwords do not match");
        }
    }
}
