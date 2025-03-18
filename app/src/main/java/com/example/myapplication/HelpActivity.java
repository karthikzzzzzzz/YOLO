package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpActivity extends AppCompatActivity {
    private ExpandableListView faqList;
    private FAQAdapter faqAdapter;
    private List<String> faqTitles;
    private HashMap<String, List<String>> faqDetails;
    private Button btnBack; // Back button reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        faqList = findViewById(R.id.faqList);
        btnBack = findViewById(R.id.help); // Initialize back button
        loadFAQData();

        faqAdapter = new FAQAdapter(this, faqTitles, faqDetails);
        faqList.setAdapter(faqAdapter);

        // Handle back button click event
        btnBack.setOnClickListener(v -> {
            finish(); // Closes HelpActivity and returns to the previous screen
        });
    }

    private void loadFAQData() {
        faqTitles = new ArrayList<>();
        faqDetails = new HashMap<>();

        // FAQ Data
        faqTitles.add("How does the pothole detection system work?");
        faqTitles.add("How to enable Drive Mode?");
        faqTitles.add("Why is my WebSocket connection failing?");
        faqTitles.add("How to report an issue?");

        List<String> ans1 = new ArrayList<>();
        ans1.add("The system uses a camera connected to Raspberry Pi with a YOLOv8 model to detect potholes and send real-time alerts to the mobile app.");

        List<String> ans2 = new ArrayList<>();
        ans2.add("Go to the home screen and tap on 'Drive Mode'. It will automatically start detecting potholes.");

        List<String> ans3 = new ArrayList<>();
        ans3.add("Ensure your Raspberry Pi is running the WebSocket server and your Android device is connected to the same network.");

        List<String> ans4 = new ArrayList<>();
        ans4.add("You can report an issue through the feedback section in the app or by contacting support at support@example.com.");

        faqDetails.put(faqTitles.get(0), ans1);
        faqDetails.put(faqTitles.get(1), ans2);
        faqDetails.put(faqTitles.get(2), ans3);
        faqDetails.put(faqTitles.get(3), ans4);
    }
}
