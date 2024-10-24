package com.example.sistlabsolos.enums;

public enum ScalesEnum {
    MAGNESIUM(
        "magnesium",
        0.19,
        0.4,
        1.0,
        2.0,
        2.1
    ),
    SAND(
        "sand",
        0.0,
        0.0,
        0.0,
        0.0,
        0.0
    ),
    ORGANIC_MATTER(
        "organic_matter",
        0.69,
        1.4,
        2.4,
        3.4,
        3.5
    ),
    CALCIUM(
        "calcium",
        0.49,
        1.0,
        2.0,
        6.0,
        6.1
    ),
    SULFUR(
        "sulfur",
        2.9,
        6.0,
        9.0,
        12.0,
        12.1
    ),
    POTASSIUM(
        "potassium",
        0.059,
        0.12,
        0.21,
        0.45,
        0.5
    ),
    ACIDITY(
        "acidity",
        0.0,
        0.0,
        0.0,
        0.0,
        0.0
    ),
    SODIUM(
        "sodium",
        0.0,
        0.0,
        0.0,
        0.0,
        0.0
    ),
    PH(
        "ph",
        3.99,
        4.4,
        4.9,
        5.5,
        5.6
    ),
    TOTAL_ORGANIC_CARBON(
        "total_organic_carbon",
        0.0,
        0.0,
        0.0,
        0.0,
        0.0
    ),
    CLAY(
        "clay",
        0.0,
        0.0,
        0.0,
        0.0,
        0.0
    ),
    PHOSPHOR(
        "phosphor",
        3.9,
        8.0,
        12.0,
        18.0,
        19.0
    ),
    ALUMINUM(
        "aluminum",
        0.29,
        0.7,
        1.5,
        2.5,
        2.6
    ),
    BASES(
        "bases",
        19.0,
        35.0,
        50.0,
        70.0,
        71.0
    ),
    CTC(
        "ctc",
        1.09,
        2.0,
        4.0,
        8.0,
        8.1
    ),
    SILT(
        "silt",
        0.0,
        0.0,
        0.0,
        0.0,
        0.0
    );

    private String name;
    private Double lower;
    private Double low;
    private Double medium;
    private Double high;
    private Double higher;

    ScalesEnum(String name, Double lower, Double low, Double medium, Double high, Double higher) {
        this.name = name;
        this.lower = lower;
        this.low = low;
        this.medium = medium;
        this.high = high;
        this.higher = higher;
    }

    public String getName() {
        return this.name;
    }

    public Double getLower() {
        return this.lower;
    }

    public Double getLow() {
        return this.low;
    }

    public Double getMedium() {
        return this.medium;
    }

    public Double getHigh() {
        return this.high;
    }

    public Double getHigher() {
        return this.higher;
    }

    







}
