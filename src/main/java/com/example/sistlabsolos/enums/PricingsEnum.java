package com.example.sistlabsolos.enums;

public enum PricingsEnum {
    BASIC(
        "basico",
        "Plano inicial limitado",
        0.00,
        5,
        3
    ), 
    PRO(
        "pro",
        "Plano pago intermediário com limites maiores",
        20.00,
        10,
        6
    ), 
    PREMIUM(
        "premium",
        "Plano com laudos ilimitados e funcionários com limite maior ",
        50.00,
        500,
        15
    ), 
    COLLEGE(
        "universidade",
        "Plano 100% ilimitado disponível apenas para universidades parceiras limitado",
        0.00,
        500,
        500
    );

    private String name;
    private String description;
    private Double value;
    private Integer reportsLimit;
    private Integer employeesLimit;

    PricingsEnum(
        String name,
        String description,
        Double value,
        Integer reportsLimit,
        Integer employeesLimit
    ){
        this.name = name;
        this.description = description;
        this.value = value;
        this.reportsLimit = reportsLimit;
        this.employeesLimit = employeesLimit;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Double getValue() {
        return this.value;
    }

    public Integer getReportsLimit() {
        return this.reportsLimit;
    }

    public Integer getEmployeesLimit() {
        return this.employeesLimit;
    }

}
