package com.xuexi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123";
        String s = encodePassword(password);
        //$2a$10$SMjwMwQz7egTjUwEJeKq8.o/E8dtKl9GSXOK/w7yE3RM.EZ6BrAVi
        //$2a$10$5IH1.sVa3UxTotGwrXcTGuTYrQgWJ2n00bNOXf1hugxWLl/ap94dG
        System.out.println(s);
    }
}
