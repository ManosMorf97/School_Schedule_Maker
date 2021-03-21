package com.example.myapplication;



import android.content.Intent;
import android.os.Bundle;



import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;

public class LessonsActivity extends KeyboardActivity {
    CheckBox[]checkBoxes;
    ArrayList<String> classes;
    EditText namecourse;
    EditText ammountofhours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        namecourse=findViewById(R.id.editTextTextPersonName2);
        checkBoxes= new CheckBox[]{findViewById(R.id.checkBox),findViewById(R.id.checkBox2),findViewById(R.id.checkBox3)};
        ammountofhours=findViewById(R.id.editTextTextPersonName4);
        Button NextLesson=findViewById(R.id.button2);
        Button Done=findViewById(R.id.button3);
        classes= new ArrayList<>();
        NextLesson.setOnClickListener(view -> {
            if(insert()) {
                classes.clear();
                EditText[] editTexts = {namecourse, ammountofhours};
                for (EditText edit_text : editTexts)
                    edit_text.getText().clear();
                for (CheckBox checkBox : checkBoxes)
                    checkBox.setChecked(false);
            }
        });
        Done.setOnClickListener(view -> {
            if(insert()) {
                Intent activityChangeIntent = new Intent(LessonsActivity.this, TeachersActivity.class);
                startActivity(activityChangeIntent);
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