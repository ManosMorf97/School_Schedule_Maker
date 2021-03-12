package com.example.myapplication;

import java.util.ArrayList;

public class DataBase {
    private final static ArrayList<Lesson> lessons=new ArrayList<>();
    private final static ArrayList<Teacher> teachers=new ArrayList<>();
    private static ArrayList<String> schedule;
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

    public static void insertLesson(String namecourse,ArrayList<String> classes,String ammountofhours){
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
