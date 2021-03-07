package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LessonsActivity extends AppCompatActivity {
    CheckBox[]checkBoxes;
    ArrayList<String> classes;
    EditText namecourse;
    EditText ammountofhours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        namecourse=findViewById(R.id.editTextTextPersonName2);
        CheckBox[] checkBoxes2={findViewById(R.id.checkBox),findViewById(R.id.checkBox2),findViewById(R.id.checkBox3)};
        checkBoxes=checkBoxes2;
        ammountofhours=findViewById(R.id.editTextTextPersonName4);
        Button NextLesson=findViewById(R.id.button2);
        Button Done=findViewById(R.id.button3);
        classes=new ArrayList<String>();
        NextLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(insert()) {
                    classes.clear();
                    EditText[] editTexts = {namecourse, ammountofhours};
                    for (EditText edit_text : editTexts)
                        edit_text.getText().clear();
                    for (CheckBox checkBox : checkBoxes)
                        checkBox.setChecked(false);
                }
            }
        });
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(insert()) {
                    Intent activityChangeIntent = new Intent(LessonsActivity.this, TeachersActivity.class);
                    startActivity(activityChangeIntent);
                }

            }
        });

    }
    public boolean insert(){
        for(CheckBox checkbox:checkBoxes){
            if(checkbox.isChecked()){
                classes.add(checkbox.getText().toString());
            }
        }
        if(classes.isEmpty()||namecourse.getText().toString().equals("")||ammountofhours.getText().toString().equals("")){
            Toast toast=Toast.makeText(getApplicationContext(),
                    "You forgot to write a field and/or forgot to select classes",Toast.LENGTH_LONG);
            toast.show();
            classes.clear();
            return false;
        }
        DataBase.insertLesson(namecourse.getText().toString(),classes,ammountofhours.getText().toString());
        return true;
    }
}