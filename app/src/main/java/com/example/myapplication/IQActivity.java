package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class IQActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iq);
        Button Schedule=findViewById(R.id.button6);
        TextView text=findViewById(R.id.textView12);
        EditText etext=findViewById(R.id.editTextTextPersonName10);

        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                text.setText("LOADING");
                String IQ=etext.getText().toString();
                ArrayList<String> schedule=Utilities.Schedule(DataBase.Lessons(),DataBase.Teachers(),IQ);
                DataBase.setSchedule(schedule);
            }
        });
    }

}