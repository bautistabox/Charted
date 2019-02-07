package com.alexbautista.charted.account;

import com.alexbautista.charted.database.ConnectionFactory;
import com.alexbautista.charted.database.ConnectionFactoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckInput {
    private static final ConnectionFactory connectionFactory = new ConnectionFactoryImpl();

    public static boolean usernameExists(String username) {
        try {
            try (Connection conn = connectionFactory.getConnection()) {
                var query = "SELECT * FROM user WHERE username=?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        var flag = 0;
                        while (rs.next()) {
                            flag += 1;
                        }
                        if (flag > 0)
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

    public static boolean isUsernameValidLength(String username) {
        if (username.length() < 8 || username.length() > 20) {
            return false;
        }
        return true;
    }

    public static boolean isUsernameValidChar(String username) {
        return (username != null) && username.matches("[A-Za-z0-9_]+");
    }

    // TODO: make password criteria stronger
    public static boolean checkPassword(String password) {
        return password.matches("[\\S]{6,16}");
    }
}
