package com.svat.svatprojectfinal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder  bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String rawPassword ="MMNNBBVV";
        String encodedPassword =bCryptPasswordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
