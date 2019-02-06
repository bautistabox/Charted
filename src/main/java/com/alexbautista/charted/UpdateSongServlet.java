package com.alexbautista.charted;

import com.alexbautista.charted.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/update")
@MultipartConfig(maxFileSize = 16177215)

public class UpdateSongServlet extends HttpServlet {
    ConnectionFactory connectionFactory = new ConnectionFactoryImpl();

    public void updateStatus(int status, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (status >= 1) {
            System.out.println("Song Updated");
            req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
        } else {
            System.out.println("Error Updating Song");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        Song song = new Song();
        resp.setContentType("text/html");

        song.setId(Integer.valueOf(CookieJar.getCookieValue("song_id", req)));

        try (Connection conn = connectionFactory.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT title, artist, genre, file, uploader_id FROM song WHERE id=?")) {
                ps.setInt(1, song.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    song.setTitle(rs.getString("title"));
                    song.setArtist(rs.getString("artist"));
                    song.setGenre(Genre.valueOf(rs.getString("genre")));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        req.setAttribute("oldTitle", song.getTitle());
        req.setAttribute("oldArtist", song.getArtist());
        req.setAttribute("oldGenre", song.getGenre());
        req.getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        Song song = new Song();
        song.setTitle(req.getParameter("newSongTitle"));
        song.setArtist(req.getParameter("newSongArtist"));
        song.setGenre(Genre.valueOf(req.getParameter("newSongGenre")));
        song.setId(Integer.valueOf(CookieJar.getCookieValue("song_id", req)));

        InputStream is = null;
        Part filePart = req.getPart("upload");
        if (filePart != null) {
            is = filePart.getInputStream();
        }

        try {
            try (Connection conn = connectionFactory.getConnection()) {
                if (is.available() > 0) {
                    System.out.println("update include file upload");
                    try (PreparedStatement ps = conn.prepareStatement(
                            "UPDATE song SET title=?, artist=?, genre=?, file=? WHERE id=?")) {
                        ps.setString(1, song.getTitle());
                        ps.setString(2, song.getArtist());
                        ps.setString(3, song.getGenre().name());
                        ps.setBlob(4, is);
                        ps.setInt(5, song.getId());

                        var status = ps.executeUpdate();
                        updateStatus(status, req, resp);
                    }
                } else {
                    try (PreparedStatement ps = conn.prepareStatement(
                            "UPDATE song SET title=?, artist=?, genre=? WHERE id=?")) {
                        ps.setString(1, song.getTitle());
                        ps.setString(2, song.getArtist());
                        ps.setString(3, song.getGenre().name());
                        ps.setInt(4, song.getId());

                        var status = ps.executeUpdate();
                        updateStatus(status, req, resp);
                    }
                    System.out.println("update DID NOT include file upload");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
