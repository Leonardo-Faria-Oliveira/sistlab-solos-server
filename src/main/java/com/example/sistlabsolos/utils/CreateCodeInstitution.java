package com.example.sistlabsolos.utils;

public class CreateCodeInstitution {
    

    public Integer createCode(String name){
        var a = name.hashCode();
        System.out.println(a);
        return a;
    }

}
