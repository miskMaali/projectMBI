package com.example.miskmbiproject;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView historyListView;
    private ArrayAdapter<BMIRecord> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = findViewById(R.id.historyListView);
        List<BMIRecord> history = BMIHistory.getInstance().getHistory();

        adapter = new ArrayAdapter<BMIRecord>(this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                history) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                BMIRecord record = (BMIRecord) getItem(position);
                text1.setText(String.format("BMI: %.1f", record.getBmi()));
                text2.setText(new SimpleDateFormat("MM/dd/yyyy HH:mm")
                        .format(record.getDate()));

                return view;
            }
        };

        historyListView.setAdapter(adapter);
    }
}