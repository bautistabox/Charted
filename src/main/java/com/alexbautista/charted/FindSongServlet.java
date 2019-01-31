package com.alexbautista.charted;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet (
        name = "findsongservlet",
        urlPatterns = "/find_song"
)

public class FindSongServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var song = (req.getParameter("searched_song"));
        String title = "";
        String artist = "";
        String genre = "";
        String file = "";
        int uploader_id = 0;

        try {
            // creating mysql DB connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/charted_schema";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "123!@#qweQWE");

            // SQL SELECT query
            var query = "SELECT title, artist, genre FROM song WHERE title=\"" + song + "\"";

            // Java Statement
            Statement st = conn.createStatement();

            // execute the query and get java resultset
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                title = rs.getString("title");
                artist = rs.getString("artist");
                genre = rs.getString("genre");
                file = "im a file";
                uploader_id = rs.getInt("uploader_id");
            }
            rs.close();
            st.close();
            conn.close();
        } catch(ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }

        req.setAttribute("title", title);
        req.setAttribute("artist", artist);
        req.setAttribute("genre", genre);

        req.getRequestDispatcher("/WEB-INF/song_page.jsp").forward(req, resp);
    }
}