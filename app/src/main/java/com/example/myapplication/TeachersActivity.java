package com.example.myapplication;

import android.content.Context;
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
    ArrayList<Lesson> screen_courses=new ArrayList<Lesson>();
    ArrayList<Lesson> selected_courses=new ArrayList<Lesson>();
    int id;
    EditText name;
    EditText max_hours_per_day;
    EditText max_hours_per_week;
    ArrayAdapter arrayAdapter;
    Context context;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        id=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        name=findViewById(R.id.editTextTextPersonName6);
        max_hours_per_day=findViewById(R.id.editTextTextPersonName7);
        max_hours_per_week=findViewById(R.id.editTextTextPersonName8);
        Button NextTeacher=findViewById(R.id.button4);
        Button Done=findViewById(R.id.button5);
        listView=findViewById(R.id.list_view);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        courses=DataBase.Lessons();
        screen_courses.addAll(courses);
        context=this;
        FirstThing();
        NextTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                EditText[] editTexts={name,max_hours_per_day,max_hours_per_week};
                screen_courses.removeAll(selected_courses);
                selected_courses.clear();
                FirstThing();
                for(EditText editText:editTexts)
                    editText.getText().clear();
            }
        });
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                Intent activityChangeIntent = new Intent(TeachersActivity.this, IQActivity.class);
                startActivity(activityChangeIntent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listView.setItemChecked(i,listView.isItemChecked(i));
                //if(!selected_courses.contains(screen_courses.get(i)))
                   // selected_courses.add(screen_courses.get(i));

            }

        });
    }
    public void insertData(){
        for(int i=0; i<screen_courses.size(); i++){
            if(listView.isItemChecked(i)){
                selected_courses.add(screen_courses.get(i));
            }
        }
        DataBase.insertTeacher(name.getText().toString()+(++id),max_hours_per_day.getText().toString(),
                max_hours_per_week.getText().toString(),selected_courses);
    }
    public void FirstThing(){
        arrayAdapter=new ArrayAdapter(context, android.R.layout.simple_list_item_multiple_choice,screen_courses);
        listView.setAdapter(arrayAdapter);
        for(int i=0; i<screen_courses.size(); i++)
            listView.setItemChecked(i,false);
    }
}