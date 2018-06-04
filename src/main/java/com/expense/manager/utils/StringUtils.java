package com.expense.manager.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {

    public static final String USER_ID = "USER_ID";

    private StringUtils(){

    }

    public static String getMD5Hash(String input){
        StringBuilder out = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte byteData[] = md.digest();
            for (int i = 0; i < byteData.length; i++) {
                out.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception e) {
        }
        return out.toString();
    }

    public static boolean isBlank(String name) {
        return name == null || name.trim().length() == 0;
    }
}
