package com.alexbautista.charted;

import com.alexbautista.charted.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(
        name = "login_servlet",
        urlPatterns = "/home"
)

public class LoginServlet extends HttpServlet {
    private final ConnectionFactory connectionFactory = new ConnectionFactoryImpl();
    private final PasswordHasher passwordHasher = new PasswordHasherImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = passwordHasher.hashPassword(req.getParameter("userpass"));
        int userId = 0;
        String userPass = null;
        try {
            try (Connection conn = connectionFactory.getConnection()) {
                try (PreparedStatement ps = conn.prepareStatement("select id, pass from user where username=?")) {
                    ps.setString(1, user);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            userPass = rs.getString("pass");
                            userId = rs.getInt("id");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        if(!CheckInput.usernameExists(user)) {
            var usernameErrorMessage = "Username doesn't exist";
            req.setAttribute("errorMessage", usernameErrorMessage);
            req.getRequestDispatcher("/").forward(req, resp);
            return;
        }

        if (!pass.equals(userPass)) {
            var passwordErrorMessage = "Incorrect Username or Password";
            req.setAttribute("errorMessage", passwordErrorMessage);
            req.getRequestDispatcher("/").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", "admin");
        session.setMaxInactiveInterval(30 * 60);
        Cookie userName = new Cookie("user", user);
        Cookie uID = new Cookie("uid", String.valueOf(userId));
        userName.setMaxAge(30 * 60);
        resp.addCookie(userName);
        resp.addCookie(uID);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);

    }
}
