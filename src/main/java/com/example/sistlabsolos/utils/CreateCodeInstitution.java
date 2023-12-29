package com.example.sistlabsolos.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class CreateCodeInstitution {
    
    public String createCode(String name){
        var code = BCrypt.withDefaults().hashToString(12, name.toCharArray());
        System.out.println(code);
        return code.substring(0,5).replace("$", "2");
    }

}
