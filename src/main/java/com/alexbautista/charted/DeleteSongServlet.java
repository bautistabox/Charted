package com.alexbautista.charted;

import com.alexbautista.charted.model.ConnectionFactory;
import com.alexbautista.charted.model.ConnectionFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/delete")

public class DeleteSongServlet extends HttpServlet {
    ConnectionFactory connectionFactory = new ConnectionFactoryImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            try (Connection conn = connectionFactory.getConnection()) {
                var query = "DELETE FROM song where id=?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, Integer.valueOf(req.getParameter("secret_id")));
                    if (ps.executeUpdate() < 1) {
                        System.out.println("error in deleting song");
                    }
                    req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
