package com.example.miskmbiproject;

import java.util.Date;

public class BMIRecord {
    private double bmi;
    private Date date;

    public BMIRecord(double bmi, Date date) {
        this.bmi = bmi;
        this.date = date;
    }

    public double getBmi() {
        return bmi;
    }
    public Date getDate() {
        return date;
    }
}