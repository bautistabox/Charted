package com.alexbautista.charted.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasherImpl implements PasswordHasher {
    public String hashPassword(String message) {
        var sb = new StringBuilder();

        try {
            var messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(message.getBytes());
            byte[] hashedBytes = messageDigest.digest();
            for (byte hashedByte : hashedBytes) {
                sb.append(String.format("%02x", hashedByte & 0xff));
            }
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Missing SHA-256 Algorithm: IMPOSSIBLE", ex);
        }

        return sb.toString();
    }
}
