package com.flock.TP_server.utils;


import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class StringUtils {
    public static String generateRandomString(int len) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";


        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            int index = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String generateHashForString(String originalString) {
        return DigestUtils.md5DigestAsHex(originalString.getBytes());
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] encodedHash = digest.digest(
//                    originalString.getBytes(StandardCharsets.UTF_8));
//            String hashedString = bytesToHex(encodedHash);
//            System.out.println(hashedString);
//            return hashedString;
//        } catch(Exception e) {
//            System.out.println("what");
//            return generateHashForString(originalString);
//        }

    }
}
