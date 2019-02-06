package com.alexbautista.charted;

import com.alexbautista.charted.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        if (CheckInput.usernameExists(username)) {
            var usernameErrorMessage = "The specified username already exists. Please choose another.";
            req.setAttribute("errorMessage", usernameErrorMessage);
            req.getRequestDispatcher("WEB-INF/signup.jsp").forward(req, resp);
            return;
        }

        if (!CheckInput.isUsernameValidLength(username)) {
            var usernameErrorMessage = "Please ensure username is between 8 and 20 characters";
            req.setAttribute("errorMessage", usernameErrorMessage);
            req.getRequestDispatcher("WEB-INF/signup.jsp").forward(req, resp);
            return;
        }

        if (!CheckInput.isUsernameValidChar(username)) {
            var usernameErrorMessage = "Please ensure username uses only letters, numbers and underscore";
            req.setAttribute("errorMessage", usernameErrorMessage);
            req.getRequestDispatcher("WEB-INF/signup.jsp").forward(req, resp);
            return;
        }

        if (!pass1.equals(pass2)) {
            var passwordErrorMessage = "Passwords do not match";
            req.setAttribute("errorMessage", passwordErrorMessage);
            req.getRequestDispatcher("WEB-INF/signup.jsp").forward(req, resp);
            return;
        }

        System.out.println("pass1: " + pass1 + "\npass2: " + pass2);
        if (!(CheckInput.checkPassword(pass1))) {
            var passwordErrorMessage = "Password must be between 6 and 16 characters and include one numeric digit";
            req.setAttribute("errorMessage", passwordErrorMessage);
            req.getRequestDispatcher("WEB-INF/signup.jsp").forward(req, resp);
            return;
        }

        var e_pass = passwordHasher.hashPassword(pass1);

        try {
            try (Connection conn = connectionFactory.getConnection()) {
                // SQL INSERT query
                var query = "INSERT into user (username, pass) VALUES (?, ?)";

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
    }
}
