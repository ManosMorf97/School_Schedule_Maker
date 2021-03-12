package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= findViewById(R.id.button);
        button.setOnClickListener(v -> {
            // Perform action on click
            Intent activityChangeIntent = new Intent(MainActivity.this, LessonsActivity.class);

            // currentContext.startActivity(activityChangeIntent);

              startActivity(activityChangeIntent);
        });
    }
}