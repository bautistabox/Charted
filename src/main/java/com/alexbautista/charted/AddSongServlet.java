package com.alexbautista.charted;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet (
        name = "addsongservlet",
        urlPatterns = "/add_song"
)

public class AddSongServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var title = req.getParameter("songTitle");
        var artist = req.getParameter("songArtist");
        var genre = req.getParameter("songGenre");
        var mastery_level = req.getParameter("knowledgeLevel");
        var file = req.getParameter("upload");
        var uploader = 1;

        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/charted_schema";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "123!@#qweQWE");

            var query = "INSERT INTO song (title, artist, genre, file, uploader_id) " +
                    "VALUES (" + title + ", " + artist + ", " + genre + ", " + file + ", " + uploader + ")";

            System.out.println(query);

            Statement st = conn.createStatement();

            var result = st.executeQuery(query);
            if (result == 1){
                System.out.println("Song Added");
            } else {
                System.out.println("");
            }

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }

    }
}
