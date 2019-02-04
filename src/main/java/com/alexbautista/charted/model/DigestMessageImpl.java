package com.alexbautista.charted.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestMessageImpl implements DigestMessage {
    public String getDigest(String message) {
        StringBuffer stringBuffer = new StringBuffer();
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(message.getBytes());
            byte[] messageDigestMD5 = messageDigest.digest();
            for (byte bytes : messageDigestMD5) {
                stringBuffer.append(String.format("%02x", bytes & 0xff));
            }
        } catch (
                NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return stringBuffer.toString();
    }
}
