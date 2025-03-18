package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Button btnReport1 = findViewById(R.id.btnReport1);
        Button btnReport2 = findViewById(R.id.btnReport2);
        Button btnReport3 = findViewById(R.id.btnReport3);
        Button btnBack = findViewById(R.id.btnBac); // Back Button


        btnReport1.setOnClickListener(v -> openReportDetails("Report 1"));
        btnReport2.setOnClickListener(v -> openReportDetails("Report 2"));
        btnReport3.setOnClickListener(v -> openReportDetails("Report 3"));

        // Back Button Functionality
        btnBack.setOnClickListener(v -> finish()); // Goes back to the previous activity
    }

    private void openReportDetails(String reportTitle) {
        Intent intent = new Intent(this, ReportDetailActivity.class);
        intent.putExtra("REPORT_TITLE", reportTitle);
        startActivity(intent);
    }
}
