package com.alexbautista.charted;

import com.alexbautista.charted.model.ConnectionFactory;
import com.alexbautista.charted.model.ConnectionFactoryImpl;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

@WebServlet(
        name = "downloadSong",
        urlPatterns = "/download_song"
)

public class DownloadSong extends HttpServlet {
    ConnectionFactory connectionFactory = new ConnectionFactoryImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            try (Connection conn = connectionFactory.getConnection()) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT file from song where id = ?")) {
                    ps.setInt(1, Integer.valueOf(req.getParameter("song_id")));

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        //get file blob data
                        Blob blob = rs.getBlob("file");

                        InputStream inputStream = blob.getBinaryStream();
                        int fileLength = inputStream.available();

                        ServletContext context = getServletContext();

                        // set MIME type for download
                        String mimeType = context.getMimeType(".pdf");
                        if (mimeType == null) {
                            mimeType = "application/pdf";
                        }

                        resp.setContentType(mimeType);
                        resp.setContentLength(fileLength);
                        String headerKey = "Content-Disposition";
                        String headerValue = "attachment; filename=\"Charted.pdf\"";
                        resp.setHeader(headerKey, headerValue);

                        // writes file to the client
                        OutputStream outputStream = resp.getOutputStream();

                        byte[] buffer = new byte[4096];
                        int bytesRead = -1;

                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }

                        inputStream.close();
                        outputStream.close();
                    } else {
                        // no file found
                        resp.getWriter().print("File not found");
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
