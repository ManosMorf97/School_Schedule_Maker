package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TeachersActivity extends AppCompatActivity {
    ArrayList<Lesson> courses;
    ArrayList<Lesson> screen_courses=new ArrayList<>();
    ArrayList<Lesson> selected_courses=new ArrayList<>();
    int id;
    EditText name;
    EditText max_hours_per_day;
    EditText max_hours_per_week;

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
        NextTeacher.setOnClickListener(view -> {
            if (insertData()) {
               DO();
            }
        });
        Done.setOnClickListener(view -> {
            if(insertData()) {
                if(screen_courses.isEmpty()) {
                    Intent activityChangeIntent = new Intent(TeachersActivity.this, IQActivity.class);
                    startActivity(activityChangeIntent);
                }else{
                    DO();
                    Toast toast=Toast.makeText(getApplicationContext(),
                            "Some Lessons do not have teachers",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        listView.setOnItemClickListener((adapterView, view, i, l) -> listView.setItemChecked(i,listView.isItemChecked(i)));
    }
    public boolean insertData(){
        for(int i=0; i<screen_courses.size(); i++){
            if(listView.isItemChecked(i)){
                selected_courses.add(screen_courses.get(i));
            }
        }
        EditText[] editTexts={name,max_hours_per_day,max_hours_per_week};
        boolean fieldsWriten=true;
        for(EditText et:editTexts){
            if(et.getText().toString().equals("")){
                fieldsWriten=false;
            }
            boolean hasLessons=!selected_courses.isEmpty();
            if(!hasLessons||!fieldsWriten){
                Toast toast=Toast.makeText(getApplicationContext(),
                        "You forgot to write a field and/or forgot to add Lessons",Toast.LENGTH_LONG);
                toast.show();
                selected_courses.clear();
                return false;
            }
        }
        DataBase.insertTeacher(name.getText().toString()+(++id),max_hours_per_day.getText().toString(),
                max_hours_per_week.getText().toString(),selected_courses);
        screen_courses.removeAll(selected_courses);
        selected_courses.clear();
        return true;
    }
    public void FirstThing(){
        ArrayAdapter<Lesson> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, screen_courses);
        listView.setAdapter(arrayAdapter);
        for(int i=0; i<screen_courses.size(); i++)
            listView.setItemChecked(i,false);
    }
    public void DO(){
        EditText[] editTexts = {name, max_hours_per_day, max_hours_per_week};
        FirstThing();
        for (EditText editText : editTexts)
            editText.getText().clear();
    }
}