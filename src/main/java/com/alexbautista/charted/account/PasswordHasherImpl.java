package com.alexbautista.charted.account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasherImpl implements PasswordHasher {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public String hashPassword(String password) {
        String ret = null;

        try {
            var messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] hashedBytes = messageDigest.digest();
            ret = bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Missing SHA-256 Algorithm: IMPOSSIBLE", ex);
        }
        return ret;
    }
}
