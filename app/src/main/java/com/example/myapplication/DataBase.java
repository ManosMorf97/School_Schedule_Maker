package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {
    private static ArrayList<Lesson> lessons=new ArrayList<Lesson>();
    private static ArrayList<Teacher> teachers=new ArrayList<Teacher>();

    public static ArrayList<Lesson> Lessons(){
        return lessons;
    }

    public static ArrayList<Teacher> Teachers(){
        return teachers;
    }

    public static void insertLesson(String code,String namecourse,String classescomma,String ammountofhours){
        List<String> classes= Arrays.asList(classescomma.split(","));
        Lesson lesson;
        for(String class_:classes){
            for(int i=1; i<=3; i++){
                lesson=new Lesson();
                lesson.setCode(code);
                lesson.setNameCourse(namecourse+"_"+class_+i);
                lesson.setClass(class_+i);
                lesson.setAmmountofhours(Integer.parseInt(ammountofhours));
                lesson.upL();
                lessons.add(lesson);
            }
        }

    }

    public static void insertTeacher(String code,String nameteacher,String maxday,String maxweek,String courses){
        Teacher teacher=new Teacher();
        teacher.setCode(code);
        teacher.setName(nameteacher);
        teacher.setMaxDay(Integer.parseInt(maxday));
        teacher.setMaxWeek(Integer.parseInt(maxweek));
        List<String> coursess=Arrays.asList(courses.split(","));
        for(String course:coursess){
            for(Lesson l:lessons){
                if(l.getNameCourse().equalsIgnoreCase(course)){
                    teacher.addLesson(l);
                    break;
                }
            }
        }
        teachers.add(teacher);
    }

}
