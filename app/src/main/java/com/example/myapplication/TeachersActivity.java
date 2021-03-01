package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TeachersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        EditText code=findViewById(R.id.editTextTextPersonName5);
        EditText name=findViewById(R.id.editTextTextPersonName6);
        EditText max_hours_per_day=findViewById(R.id.editTextTextPersonName7);
        EditText max_hours_per_week=findViewById(R.id.editTextTextPersonName8);
        EditText lessons=findViewById(R.id.editTextTextPersonName9);
        Button NextTeacher=findViewById(R.id.button4);
        Button Done=findViewById(R.id.button5);
        NextTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase.insertTeacher(code.getText().toString(),name.getText().toString(),max_hours_per_day.getText().toString(),
                        max_hours_per_week.getText().toString(),lessons.getText().toString());
                EditText[] editTexts={code,name,max_hours_per_day,max_hours_per_week,lessons};
                for(EditText editText:editTexts)
                    editText.getText().clear();
            }
        });
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityChangeIntent = new Intent(TeachersActivity.this, IQActivity.class);
                startActivity(activityChangeIntent);
            }
        });
    }
}