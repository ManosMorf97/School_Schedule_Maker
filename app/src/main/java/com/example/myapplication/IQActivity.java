package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class IQActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iq);
        Button Schedule=findViewById(R.id.button6);
        EditText etext=findViewById(R.id.editTextTextPersonName10);
        ProgressBar progressBar=findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.GONE);

        Schedule.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String IQ=etext.getText().toString();
            DataBase.setSchedule(Utilities.Schedule(DataBase.Lessons(),DataBase.Teachers(),IQ));
            Intent activityChangeIntent = new Intent(IQActivity.this, Final_Activity.class);
            startActivity(activityChangeIntent);
        });
    }

}