package com.alexbautista.charted;

import com.alexbautista.charted.model.ConnectionFactory;
import com.alexbautista.charted.model.ConnectionFactoryImpl;
import com.alexbautista.charted.model.Genre;
import com.alexbautista.charted.model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

@WebServlet(
        name = "addsongservlet",
        urlPatterns = "/add_song"
)

@MultipartConfig(maxFileSize = 16177215)

public class AddSongServlet extends HttpServlet {
    private final ConnectionFactory connectionFactory = new ConnectionFactoryImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/add_song.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        var masteryLevel = Integer.valueOf(req.getParameter("knowledgeLevel"));

        Cookie ck[] = req.getCookies();
        int uId = 0;
        System.out.println(ck[0].getValue());

        for (var c : ck) {
            if (c.getName().equals("uid")) {
                uId = Integer.valueOf(c.getValue());
            }
        }

        Song song = new Song(
                req.getParameter("songTitle"),
                req.getParameter("songArtist"),
                Genre.valueOf(req.getParameter("songGenre")),
                uId
        );

        //file input
        InputStream inputStream = null; // input stream of the upload file

        // obtain the upload file part in this multipart request
        Part filePart = req.getPart("upload");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

        try {
            int song_id = 0;
            try (Connection conn = connectionFactory.getConnection()) {
                var query1 = "INSERT INTO song (title, artist, genre, file, uploader_id) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(query1)) {
                    ps.setString(1, song.getTitle());
                    ps.setString(2, song.getArtist());
                    ps.setString(3, song.getGenre().name());
                    ps.setInt(5, song.getUploader());

                    if (inputStream != null) {
                        System.out.println("input stream is not null");
                        ps.setBlob(4, inputStream);
                    } else {
                        System.out.println("Input stream IS null");
                    }

                    var row = ps.executeUpdate();

                    if (row >= 1) {
                        System.out.println("Song Added");
                    } else {
                        System.out.println("Error Adding Song");
                    }

                }
                var query2 = "select id from song where title=?";
                try (PreparedStatement ps = conn.prepareStatement(query2)) {
                    ps.setString(1, song.getTitle());

                    try (var rs = ps.executeQuery()) {
                        while (rs.next()) {
                            song_id = rs.getInt("id");
                        }
                    }
                }
                var query3 = "INSERT INTO song_mastery (song_id, user_id, mastery_level) VALUES (?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(query3)) {
                    ps.setInt(1, song_id);
                    ps.setInt(2, song.getUploader());
                    ps.setInt(3, masteryLevel);

                    var row = ps.executeUpdate();

                    if (row >= 1) {
                        System.out.println("Level Added");
                        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
                    } else {
                        System.out.println("Error Adding Level");
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
