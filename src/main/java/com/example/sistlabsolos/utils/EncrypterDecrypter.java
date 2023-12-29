package com.example.sistlabsolos.utils;

import java.beans.JavaBean;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class EncrypterDecrypter {
    
    public static String encrypt(String str){
        return BCrypt.withDefaults().hashToString(12, str.toCharArray());
    }


}
