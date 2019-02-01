package com.alexbautista.charted;

import com.alexbautista.charted.model.ConnectionFactory;
import com.alexbautista.charted.model.ConnectionFactoryImpl;
import com.alexbautista.charted.model.Genre;
import com.alexbautista.charted.model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

        try {
            try(Connection conn = connectionFactory.getConnection()) {
                // SQL SELECT query
                var query = "SELECT id, title, artist, genre, uploader_id FROM song WHERE title=?";
                // Java Statement
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, songQuery);
                    // execute the query and get java resultset
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            song.setId(rs.getInt("id"));
                            song.setTitle(rs.getString("title"));
                            song.setArtist(rs.getString("artist"));
                            song.setGenre(Genre.valueOf(rs.getString("genre")));
                            song.setUploader(rs.getInt("uploader_id"));
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
        req.setAttribute("uploader", song.getUploader());
        req.getRequestDispatcher("/WEB-INF/song_page.jsp").forward(req, resp);
    }
}