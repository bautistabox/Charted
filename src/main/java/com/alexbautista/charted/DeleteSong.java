package com.alexbautista.charted;

import com.alexbautista.charted.model.ConnectionFactory;
import com.alexbautista.charted.model.ConnectionFactoryImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/delete")

public class DeleteSong extends HttpServlet {
    ConnectionFactory connectionFactory = new ConnectionFactoryImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            try (Connection conn = connectionFactory.getConnection()) {
                var query = "DELETE FROM song where id=?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, Integer.valueOf(req.getParameter("song_id")));

                    if (ps.executeUpdate() >= 1) {
                        resp.sendRedirect("home.jsp");
                    } else {
                        System.out.println("error in deleting song");
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
