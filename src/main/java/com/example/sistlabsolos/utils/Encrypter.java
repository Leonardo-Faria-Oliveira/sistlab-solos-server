package com.example.sistlabsolos.utils;


import at.favre.lib.crypto.bcrypt.BCrypt;

public class Encrypter {
    
    public static String encrypt(String str){
        return BCrypt.withDefaults().hashToString(12, str.toCharArray());
    }


}
