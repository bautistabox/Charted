package com.alexbautista.charted;

import com.alexbautista.charted.model.ConnectionFactory;
import com.alexbautista.charted.model.ConnectionFactoryImpl;
import com.alexbautista.charted.model.Genre;
import com.alexbautista.charted.model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(
        name = "findsongservlet",
        urlPatterns = "/find_song"
)

public class FindSongServlet extends HttpServlet {
    private final ConnectionFactory connectionFactory = new ConnectionFactoryImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var songQuery = req.getParameter("searched_song");
        Song song = new Song();
        var level = "";
        var uploader = "";

        try {
            try (Connection conn = connectionFactory.getConnection()) {
                // SQL SELECT query
                var query1 = "SELECT id, title, artist, genre, uploader_id FROM song WHERE title=?";
                // Java Statement
                try (PreparedStatement ps = conn.prepareStatement(query1)) {
                    ps.setString(1, songQuery);
                    // execute the query and get java resultset
                    try (ResultSet rs = ps.executeQuery()) {
                        var flag = 0;
                        while (rs.next()) {
                            song.setId(rs.getInt("id"));
                            song.setTitle(rs.getString("title"));
                            song.setArtist(rs.getString("artist"));
                            song.setGenre(Genre.valueOf(rs.getString("genre")));
                            song.setUploader(rs.getInt("uploader_id"));
                            flag += 1;
                        }
                        if (flag < 1) {
                            req.getRequestDispatcher("/WEB-INF/empty_results.jsp").forward(req, resp);
                        }
                    }
                }
                var query2 = "SELECT mastery_level FROM song_mastery WHERE song_id=? AND user_id=?";
                try (PreparedStatement ps = conn.prepareStatement(query2)) {
                    ps.setInt(1, song.getId());
                    ps.setInt(2, song.getUploader()); // need to change this to session user
                    var levelInt = 0;

                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            levelInt = rs.getInt("mastery_level");
                        }
                    }

                    switch (levelInt) {
                        case 1:
                            level = "You've listened to it";
                            break;
                        case 2:
                            level = "You're an amateur";
                            break;
                        case 3:
                            level = "You're average";
                            break;
                        case 4:
                            level = "You're a semi-pro";
                            break;
                        case 5:
                            level = "You're a pro";
                            break;
                        default:
                            level = "Have you even listened?";
                            break;
                    }

                }
                // get the uploader's username
                var query3 = "SELECT email FROM user WHERE id=?";
                try (PreparedStatement ps = conn.prepareStatement(query3)) {
                    ps.setInt(1, song.getUploader());

                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            uploader = rs.getString("email");
                        }
                    }
                }


            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        req.setAttribute("id", song.getId());
        req.setAttribute("title", song.getTitle());
        req.setAttribute("artist", song.getArtist());
        req.setAttribute("genre", song.getGenre());
        req.setAttribute("uploader", uploader);
        req.setAttribute("level", level);
        req.getRequestDispatcher("/WEB-INF/song_page.jsp").forward(req, resp);
    }
}