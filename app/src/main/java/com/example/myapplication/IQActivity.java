package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;



public class IQActivity extends AppCompatActivity {
    Button Schedule;
    EditText etext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iq);
        Schedule=findViewById(R.id.button6);
        etext=findViewById(R.id.editTextTextPersonName10);

        Schedule.setOnClickListener(view -> {
           Async execute=new Async();
           execute.execute();
        });
    }
    @SuppressLint("StaticFieldLeak")
    private final class Async extends AsyncTask<Void,Void,Void>{
        private final ProgressDialog dialog = new ProgressDialog(IQActivity.this);
        @Override
        protected Void doInBackground(Void... voids) {
            String IQ = etext.getText().toString();
            DataBase.setSchedule(Utilities.Schedule(DataBase.Lessons(), DataBase.Teachers(), IQ));
            return null;
        }
        @Override
        protected void onPreExecute() {
            dialog.setTitle("Loading this maybe take some time");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(dialog.isShowing())
                dialog.dismiss();
            Intent activityChangeIntent = new Intent(IQActivity.this, Final_Activity.class);
            startActivity(activityChangeIntent);
        }
    }

}