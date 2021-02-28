package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LessonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        EditText code=findViewById(R.id.editTextTextPersonName);
        EditText namecourse=findViewById(R.id.editTextTextPersonName2);
        EditText classes=findViewById(R.id.editTextTextPersonName3);
        EditText ammountofhours=findViewById(R.id.editTextTextPersonName4);
        Button NextLesson=findViewById(R.id.button2);
        Button Done=findViewById(R.id.button3);
        NextLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase.insertLesson(code.getText().toString(),namecourse.getText().toString(),
                        classes.getText().toString(),ammountofhours.getText().toString());
                EditText[] editTexts={code,namecourse,classes,ammountofhours};
                for(EditText edit_text:editTexts)
                    edit_text.getText().clear();
            }
        });
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityChangeIntent = new Intent(LessonsActivity.this, TeachersActivity.class);
                startActivity(activityChangeIntent);

            }
        });

    }
}