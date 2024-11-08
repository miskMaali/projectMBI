package com.example.miskmbiproject;

import java.util.ArrayList;
import java.util.List;

public class BMIHistory {
    private static BMIHistory instance;
    private List<BMIRecord> history;

    private BMIHistory() {
        history = new ArrayList<>();
    }

    public static BMIHistory getInstance() {
        if (instance == null) {
            instance = new BMIHistory();
        }
        return instance;
    }

    public void addRecord(BMIRecord record) {
        history.add(record);
    }

    public List<BMIRecord> getHistory() {
        return new ArrayList<>(history);
    }
}