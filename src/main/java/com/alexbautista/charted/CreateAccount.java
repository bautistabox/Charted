package com.alexbautista.charted;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        name = "create_account",
        urlPatterns = "/create_account"
)

public class CreateAccount extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        var username = req.getParameter("user");
        var pass1 = req.getParameter("pass1");
        var pass2 = req.getParameter("pass2");

        if (pass1.equals(pass2)) {
            try {
                // creating mysql DB connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost/charted_schema";
                Class.forName(myDriver);
                try (Connection conn = DriverManager.getConnection(myUrl, "root", "123!@#qweQWE")) {

                    // SQL INSERT query
                    var query = "INSERT into user (email, pass) VALUES (?, ?)";

                    // Java Statement
                    try (PreparedStatement ps = conn.prepareStatement(query)) {
                        ps.setString(1, username);
                        ps.setString(2, pass1);
                        // execute the query and get java resultset
                        var result = ps.executeUpdate();
                        if (result == 1) {
                            System.out.println("Account Created");
                        } else {
                            System.out.println("Creating Error");
                        }
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            PrintWriter out = resp.getWriter();
            out.print("Passwords do not match");
        }
    }
}
