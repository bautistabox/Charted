package com.alexbautista.charted;

import com.alexbautista.charted.model.*;

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
        int uId = Integer.valueOf(CookieJar.getCookieValue("uid", req));

        Song song = new Song(
                req.getParameter("songTitle"),
                req.getParameter("songArtist"),
                Genre.valueOf(req.getParameter("songGenre")),
                uId
        );

        // check if song with same title already exists
        try {
            try (Connection conn = connectionFactory.getConnection()) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT title FROM song WHERE title=?")) {
                    ps.setString(1, song.getTitle());
                    try(ResultSet rs = ps.executeQuery()) {
                        var flag = 0;
                        while (rs.next()) {
                            req.setAttribute("title", rs.getString("title"));
                            flag += 1;
                        }
                        if (flag >= 1) {
                            req.getRequestDispatcher("/WEB-INF/exists.jsp").forward(req, resp);
                            return;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        //file input
        InputStream inputStream = null; // input stream of the upload file

        // obtain the upload file part in this multipart request
        Part filePart = req.getPart("upload");
        if (filePart != null) {
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

        try {
            try (Connection conn = connectionFactory.getConnection()) {
                var query1 = "INSERT INTO song (title, artist, genre, file, uploader_id) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(query1)) {
                    ps.setString(1, song.getTitle());
                    ps.setString(2, song.getArtist());
                    ps.setString(3, song.getGenre().name());
                    ps.setInt(5, song.getUploader());

                    if (inputStream != null) {
                        ps.setBlob(4, inputStream);
                    } else {
                        System.out.println("Input stream IS null");
                    }
                    var status = ps.executeUpdate();
                    if (status >= 1) {
                        System.out.println("Song Added");
                        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
                    } else {
                        System.out.println("Error Adding Song");
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
