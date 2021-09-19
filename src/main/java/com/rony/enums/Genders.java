package com.rony.enums;

public enum Genders {
    MALE("M"),
    FEMALE("F"),
    OTHERS("O");

    public final String gender;
    Genders(String gender) {
        this.gender = gender;
    }
}
