package com.example.myapplication;

import java.io.*;
import java.util.ArrayList;

public class Utilities {
    public  static Lesson passbyValue(Lesson lesson){
        if (lesson==null) return null;
        Lesson new_lesson=new Lesson();
        new_lesson.setCode(lesson.getCode());
        new_lesson.setNameCourse(lesson.getNameCourse());
        new_lesson.setClass(lesson.getClassABC());
        new_lesson.setAmmountofhours(lesson.getAmmount());
        new_lesson.setAmoh(lesson.getAmoh());
        new_lesson.setActive(lesson.Active());
        return new_lesson;
    }
    public static Teacher passbyValue(Teacher teacher){
        if (teacher==null) return null;
        Teacher new_teacher=new Teacher();
        new_teacher.setCode(teacher.getCode());
        new_teacher.setName(teacher.getName());
        new_teacher.setW(teacher.getW());
        for(int i=0; i<5; i++){
            new_teacher.setD(i,teacher.getD(i));
        }
        for(Lesson lesson: teacher.getCodesoflessons()){
            new_teacher.getCodesoflessons().add(Utilities.passbyValue(lesson));
        }
        return new_teacher;
    }
    public static State passbyValue(State state){
        if (state==null) return  null;
        ArrayList<Lesson> lessons=new ArrayList<>();
        ArrayList<Teacher> teachers=new ArrayList<>();
        for(Lesson lesson:state.getLessons())
            lessons.add(passbyValue(lesson));
        for(Teacher teacher:state.getTeachers())
            teachers.add(passbyValue(teacher));
        State new_state=new State(lessons,teachers);
        new_state.setX(state.getX());
        new_state.setY(state.getY());
        new_state.setZ(state.getZ());
        for(int i=0; i<5; i++){
            for(int j=0; j<7; j++){
                for(int k=0; k<9; k++){
                        new_state.setNameLesson(Utilities.passbyValue(state.getNameLesson(i,j,k)),i,j,k);
                        new_state.setNameTeacher(Utilities.passbyValue(state.getNameTeacher(i,j,k)),i,j,k);
                }
            }
        }
        return new_state;
    }
    public static StateNode getBestChild(int n, State state, int value, int depth){
        if(n==depth||state.isTerminal()){
            return new StateNode(state,value);
        }else{
            State states[]=new State[state.getLessons().size()];
            ArrayList<StateNode> SN=new ArrayList<>();
            int values[]=new int[states.length];
            int maxindex=0;
            for(int i=0; i<states.length; i++){
                states[i]=Utilities.passbyValue(state);
                boolean written=states[i].write(states[i].getLessons().get(i),false);
                values[i]=value;
                if(written) {
                    values[i] = value + states[i].getpriority();
                    SN.add(getBestChild(n + 1, states[i], values[i],depth));
                }
                if(!SN.isEmpty())
                    if(SN.get(SN.size()-1).getValue()>=SN.get(maxindex).getValue()) {
                        maxindex = SN.size()-1;
                    }
            }
            if(SN.isEmpty()) {
                state.write(null,true);
                return new StateNode(state,value);
            }
            return SN.get(maxindex);
        }
    }
    static String GO(ArrayList<Lesson> lessons,ArrayList<Teacher> teachers,String IQ){
    int max=0;//we will use it for evenly spread writing
    int depth=Integer.parseInt(IQ);
    State state=new State(lessons,teachers);
    while(!state.isTerminal()){
        state=Utilities.getBestChild(0,state,0,depth).getState();
    }
    Lesson [][][] program=state.Program();
    String day[]=new String[5];
    day[0]="Monday";
    day[1]="Tuesday";
    day[2]="Wednesday";
    day[3]="Thursday";
    day[4]="Firday";
    String sector[]=new String[9];
    sector[0]="A1";
    sector[1]="A2";
    sector[2]="A3";
    sector[3]="B1";
    sector[4]="B2";
    sector[5]="B3";
    sector[6]="C1";
    sector[7]="C2";
    sector[8]="C3";
        String bw="";
        bw+="Program of school\n";
        int yt;
        String wr;
        for(int x=0; x<5; x++){
            bw+="\n"+day[x]+"\n";
            for(int y=0; y<7; y++){
                yt=y+1;
                bw+=""+yt+".)";
                for(int z=0; z<9; z++){
                    if(program[x][y][z]!=null&&program[x][y][z].getNameCourse()!=null){
                        bw+=sector[z]+" "+program[x][y][z].getNameCourse();
                        wr=program[x][y][z].getNameCourse();
                    }
                    else {
                        bw+=sector[z]+" "+"FREE TIME";
                        wr="FREE TIME";
                    }
                    for(int ui=0; ui<=(max-wr.length()); ui++)//evely spread writing
                        bw+=" ";

                }
                bw+="\n";
            }
        }
        return bw;

    }
}
