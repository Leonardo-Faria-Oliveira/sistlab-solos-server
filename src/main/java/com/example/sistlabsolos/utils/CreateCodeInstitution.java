package com.example.sistlabsolos.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class CreateCodeInstitution {
    
    public  static String createCode(String name){
        var code = BCrypt.withDefaults().hashToString(12, name.toCharArray());
        System.out.println(code);
        Integer num = (int) Math.floor(Math.random());
        return code.substring(0,8).replace("$",  num.toString());
    }

}
