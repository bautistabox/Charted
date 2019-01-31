package com.alexbautista.charted;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

@WebServlet (
        name = "addsongservlet",
        urlPatterns = "/add_song"
)

@MultipartConfig(maxFileSize = 16177215)

public class AddSongServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var title = req.getParameter("songTitle");
        var artist = req.getParameter("songArtist");
        var genre = req.getParameter("songGenre");
        var masteryLevel = req.getParameter("knowledgeLevel");
        var uploader = 1; // need to grab from session

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
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/charted_schema";
            Class.forName(myDriver);
            try (Connection conn = DriverManager.getConnection(myUrl, "root", "123!@#qweQWE")) {

                var query = "INSERT INTO song (title, artist, genre, file, uploader_id) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, title);
                    ps.setString(2, artist);
                    ps.setString(3, genre);
                    ps.setInt(5, uploader);

                    if (inputStream != null) {
                        System.out.println("input stream is not null");
                        ps.setBlob(4, inputStream);
                    } else {
                        System.out.println("Input stream IS null");
                    }

                    var row = ps.executeUpdate();
                    if (row > 0) {
                        System.out.println();
                    }

                    if (row == 1) {
                        System.out.println("Song Added");
                    } else {
                        System.out.println("Error Adding Song");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
