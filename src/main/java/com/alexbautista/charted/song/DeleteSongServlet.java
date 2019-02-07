package com.alexbautista.charted.song;

import com.alexbautista.charted.database.ConnectionFactory;
import com.alexbautista.charted.database.ConnectionFactoryImpl;

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
        var session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("/");
            return;
        }

        try {
            try (Connection conn = connectionFactory.getConnection()) {
                var query = "DELETE FROM song where id=?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, Integer.valueOf(req.getParameter("songId")));
                    if (ps.executeUpdate() < 1) {
                        System.out.println("error in deleting song");
                    }
                    resp.sendRedirect("/home");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
