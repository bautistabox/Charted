package com.alexbautista.charted.model;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckInput {
    private static final ConnectionFactory connectionFactory = new ConnectionFactoryImpl();

    public static boolean usernameExists(String username) {
        try {
            try (Connection conn = connectionFactory.getConnection()) {
                var query = "SELECT * FROM user WHERE email=?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        var flag = 0;
                        while (rs.next()) {
                            flag += 1;
                        }
                        if(flag > 0)
                            return true;
                        else
                            return false;
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean checkPassword(String password) {
        if (password.length() < 8) {
            return false;
        } else {
            return true;
        }
    }
}
