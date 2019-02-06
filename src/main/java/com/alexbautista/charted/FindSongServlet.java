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

        Cookie cookie = new Cookie("song_id", String.valueOf(song.getId()));
        resp.addCookie(cookie);

        req.setAttribute("id", song.getId());
        req.setAttribute("title", song.getTitle());
        req.setAttribute("artist", song.getArtist());
        req.setAttribute("genre", song.getGenre());
        req.setAttribute("uploader", uploader);
        req.getRequestDispatcher("/WEB-INF/song_page.jsp").forward(req, resp);
    }
}