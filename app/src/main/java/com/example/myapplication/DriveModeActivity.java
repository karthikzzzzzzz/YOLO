package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;

public class DriveModeActivity extends AppCompatActivity {
    private WebSocket webSocket;
    private MediaPlayer mediaPlayer;
    private TextView statusText;
    private ImageView driveImage;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);

        statusText = findViewById(R.id.statusText);
        driveImage = findViewById(R.id.driveImage);
        btnBack = findViewById(R.id.hel); // Ensure correct ID from XML

        // Load the alert sound
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alert);

        // Start WebSocket connection automatically
        connectToWebSocket();

        // Handle back button click
        btnBack.setOnClickListener(v -> {
            finish(); // Closes DriveModeActivity and returns to the previous screen
        });
    }

    private void connectToWebSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://10.0.2.2:8000/ws").build();
        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                runOnUiThread(() -> {
                    statusText.setText("🚗 Drive Mode: Connected ✅");
                    statusText.setTextColor(getResources().getColor(R.color.green));
                });
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                Log.d("WebSocket", "Received: " + text);
                runOnUiThread(() -> {
                    if (text != null) {
                        statusText.setText("🚨 Pothole detected! Drive safely.");
                        statusText.setTextColor(getResources().getColor(R.color.red));
                        mediaPlayer.seekTo(0);
                        mediaPlayer.start();
                    }
                });
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                runOnUiThread(() -> {
                    statusText.setText("❌ Disconnected from server.");
                    statusText.setTextColor(getResources().getColor(R.color.red));
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webSocket != null) webSocket.close(1000, "Activity destroyed");
        if (mediaPlayer != null) mediaPlayer.release();
    }
}
