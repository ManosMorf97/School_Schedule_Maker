package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {
    private static ArrayList<Lesson> lessons=new ArrayList<Lesson>();
    private static ArrayList<Teacher> teachers=new ArrayList<Teacher>();
    private static ArrayList<String> schedule;
    private static ArrayList<Lesson> checked_lessons=new ArrayList<>();
    public static ArrayList<String> Schedule(){
        return schedule;
    }
    public static void setSchedule(ArrayList<String> schedule_){
        schedule=schedule_;
    }

    public static ArrayList<Lesson> Lessons(){
        return lessons;
    }

    public static ArrayList<Teacher> Teachers(){
        return teachers;
    }

    public static void insertLesson(String namecourse,String classescomma,String ammountofhours){
        List<String> classes= Arrays.asList(classescomma.split(","));
        Lesson lesson;
        for(String class_:classes){
            for(int i=1; i<=3; i++){
                lesson=new Lesson();
                lesson.setNameCourse(namecourse+"_"+class_+i);
                lesson.setClass(class_+i);
                lesson.setAmmountofhours(Integer.parseInt(ammountofhours));
                lesson.upL();
                lessons.add(lesson);
            }
        }

    }

    public static void insertTeacher(String nameteacher,String maxday,String maxweek,ArrayList<Lesson>courses){
        Teacher teacher=new Teacher();
        teacher.setName(nameteacher);
        teacher.setMaxDay(Integer.parseInt(maxday));
        teacher.setMaxWeek(Integer.parseInt(maxweek));
        for(Lesson lesson: courses){
            teacher.addLesson(lesson);
        }
        teachers.add(teacher);
    }

}
