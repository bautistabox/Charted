package com.alexbautista.charted;

import com.alexbautista.charted.model.ConnectionFactory;
import com.alexbautista.charted.model.ConnectionFactoryImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/update")

public class UpdateSong extends HttpServlet {
/*
    ConnectionFactory connectionFactory = new ConnectionFactoryImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            try (Connection conn = connectionFactory.getConnection()) {

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
*/
}
