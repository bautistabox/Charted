package com.alexbautista.charted;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet (
        name = "create_account",
        urlPatterns = "/create_account"
)

public class CreateAccount extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        var username = req.getParameter("user");
        var pass1 = req.getParameter("pass1");
        var pass2 = req.getParameter("pass2");

        if(pass1.equals(pass2)) {
            try {
                // creating mysql DB connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost/charted_schema";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "123!@#qweQWE");

                // SQL INSERT query
                var query = "INSERT into user (email, pass) VALUES (\"" + username + "\", \"" + pass1 + "\")";

                // Java Statement
                Statement st = conn.createStatement();

                // execute the query and get java resultset
                var result = st.executeUpdate(query);
                if (result == 1){
                    System.out.println("Account Created");
                } else {
                    System.out.println("Creating Error");
                }

                st.close();
                conn.close();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            PrintWriter out = resp.getWriter();
            out.print("Passwords do not match");
        }
    }
}
