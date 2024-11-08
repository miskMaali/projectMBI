package com.example.miskmbiproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miskmbiproject.R;
import com.example.miskmbiproject.ResultActivity;

public class MainActivity extends AppCompatActivity {
    private EditText heightInput;
    private EditText weightInput;
    private Spinner heightUnitSpinner;
    private Spinner weightUnitSpinner;
    private Button calculateButton;
    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
        heightUnitSpinner = findViewById(R.id.heightUnitSpinner);
        weightUnitSpinner = findViewById(R.id.weightUnitSpinner);
        calculateButton = findViewById(R.id.calculateButton);
        logoImage = findViewById(R.id.logoImage);

        // Setup spinners
        ArrayAdapter<CharSequence> heightAdapter = ArrayAdapter.createFromResource(
                this, R.array.height_units, android.R.layout.simple_spinner_item);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightUnitSpinner.setAdapter(heightAdapter);

        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(
                this, R.array.weight_units, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightUnitSpinner.setAdapter(weightAdapter);

        calculateButton.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        try {
            double height = Double.parseDouble(heightInput.getText().toString());
            double weight = Double.parseDouble(weightInput.getText().toString());

            // Convert to meters if needed
            if (heightUnitSpinner.getSelectedItem().toString().equals("inches")) {
                height = height * 0.0254;
            } else if (heightUnitSpinner.getSelectedItem().toString().equals("cm")) {
                height = height / 100;
            }

            // Convert to kg if needed
            if (weightUnitSpinner.getSelectedItem().toString().equals("pounds")) {
                weight = weight * 0.453592;
            }

            double bmi = weight / (height * height);

            // Start result activity
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("BMI_RESULT", bmi);
            startActivity(intent);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}