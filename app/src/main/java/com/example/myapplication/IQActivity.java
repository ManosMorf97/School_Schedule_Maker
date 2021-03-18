package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class IQActivity extends AppCompatActivity {
    Button Schedule;
    EditText etext;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iq);
        Schedule=findViewById(R.id.button6);
        etext=findViewById(R.id.editTextTextPersonName10);
        progressBar=findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.GONE);

        Schedule.setOnClickListener(view -> {
           Async execute=new Async();
           execute.execute();
            progressBar.setVisibility(View.VISIBLE);
            String IQ=etext.getText().toString();
            DataBase.setSchedule(Utilities.Schedule(DataBase.Lessons(),DataBase.Teachers(),IQ));

        });
    }
    @SuppressLint("StaticFieldLeak")
    private final class Async extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            String IQ = etext.getText().toString();
            DataBase.setSchedule(Utilities.Schedule(DataBase.Lessons(), DataBase.Teachers(), IQ));
            return null;
        }
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent activityChangeIntent = new Intent(IQActivity.this, Final_Activity.class);
            startActivity(activityChangeIntent);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}