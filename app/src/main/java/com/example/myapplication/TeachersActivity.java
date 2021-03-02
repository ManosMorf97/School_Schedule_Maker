package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class TeachersActivity extends AppCompatActivity {
    ArrayList<Lesson> courses;
    ArrayList<Lesson> selected_courses=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        EditText name=findViewById(R.id.editTextTextPersonName6);
        EditText max_hours_per_day=findViewById(R.id.editTextTextPersonName7);
        EditText max_hours_per_week=findViewById(R.id.editTextTextPersonName8);
        Button NextTeacher=findViewById(R.id.button4);
        Button Done=findViewById(R.id.button5);
        ListView listView=findViewById(R.id.list_view);
        courses=DataBase.Lessons();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,courses);
        listView.setAdapter(arrayAdapter);
        NextTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase.insertTeacher(name.getText().toString(),max_hours_per_day.getText().toString(),
                        max_hours_per_week.getText().toString(),selected_courses);
                EditText[] editTexts={name,max_hours_per_day,max_hours_per_week};
                for(EditText editText:editTexts)
                    editText.getText().clear();
            }
        });
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase.insertTeacher(name.getText().toString(),max_hours_per_day.getText().toString(),
                        max_hours_per_week.getText().toString(),selected_courses);
                Intent activityChangeIntent = new Intent(TeachersActivity.this, IQActivity.class);
                startActivity(activityChangeIntent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!selected_courses.contains(courses.get(i)))
                    selected_courses.add(courses.get(i));

            }

        });
    }
}