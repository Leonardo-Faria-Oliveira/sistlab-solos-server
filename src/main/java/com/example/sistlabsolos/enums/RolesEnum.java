package com.example.sistlabsolos.enums;

public enum RolesEnum {

    ADMIN("admin"), 
    LAB_ADMIN("labAdminEmployee"),
    EMPLOYEE("employee"), 
    LAB_ADMIN_TECHNICAL("labAdminTechnical"),
    TECHNICAL("technicalResponsible");

    private String label;

    RolesEnum(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }

}
