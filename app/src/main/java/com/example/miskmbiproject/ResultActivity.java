package com.example.miskmbiproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    private TextView bmiResultText;
    private TextView bmiCategoryText;
    private Button historyButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        bmiResultText =findViewById(R.id.bmiResultText);
        bmiCategoryText =findViewById(R.id.bmiCategoryText);
        historyButton =findViewById(R.id.historyButton);
        backButton= findViewById(R.id.backButton);


        double bmi = getIntent().getDoubleExtra("BMI_RESULT", 0);
        bmiResultText.setText(String.format("Your BMI: %.1f", bmi));
        bmiCategoryText.setText(getBMICategory(bmi));

        // Save to history
        BMIRecord record = new BMIRecord(bmi, new Date());
        BMIHistory.getInstance().addRecord(record);

        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(v -> finish());
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal weight";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }
}