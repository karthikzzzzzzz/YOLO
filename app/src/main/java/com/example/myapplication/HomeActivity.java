package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize buttons
        Button btnDriveMode = findViewById(R.id.btnDriveMode);
        Button btnHome = findViewById(R.id.btnHome);
        Button btnReport = findViewById(R.id.btnReport);
        Button btnHelp = findViewById(R.id.btnHelp);

        // Navigate to Drive Mode Activity
        btnDriveMode.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, DriveModeActivity.class);
            startActivity(intent);
        });

        // Navigation for bottom menu buttons
        btnHome.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, HomeActivity.class)));
        btnReport.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ReportActivity.class)));
        btnHelp.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, HelpActivity.class)));
    }
}
