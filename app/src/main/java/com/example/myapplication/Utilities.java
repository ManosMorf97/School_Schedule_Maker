package com.example.myapplication;

import java.util.ArrayList;

public class Utilities {
    public  static Lesson passbyValue(Lesson lesson){
        if (lesson==null) return null;
        Lesson new_lesson=new Lesson();
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
            State[] states=new State[state.getLessons().size()];
            ArrayList<StateNode> SN=new ArrayList<>();
            int []values=new int[states.length];
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
    private static Lesson [][][]GO(ArrayList<Lesson> lessons,ArrayList<Teacher> teachers,String IQ){
        int depth=Integer.parseInt(IQ);
        State state=new State(lessons,teachers);
        while(!state.isTerminal()){
            state=Utilities.getBestChild(0,state,0,depth).getState();
        }
        return state.Program();
    }

    public static ArrayList<String> Schedule(ArrayList<Lesson> lessons,ArrayList<Teacher> teachers,String IQ){
        String []day={"Monday","Tuesday","Wednesday","Thursday","Friday"};
        String[] sector={"A1","A2","A3","B1","B2","B3","C1","C2","C3"};
        ArrayList<String>Schedule=new ArrayList<>();
        Lesson [][][] Lessons=GO( lessons,teachers,IQ);
        Schedule.add("Schedule of School");
        for(int x=0; x<5; x++){
            Schedule.add(day[x]);
            for(int z=0; z<9; z++){
                Schedule.add(sector[z]);
                for(int y=0; y<7; y++){
                    int v=y+1;
                    String writen;
                    if(Lessons[x][y][z]!=null&&Lessons[x][y][z].getNameCourse()!=null){
                        String LE=Lessons[x][y][z].getNameCourse();
                        writen=LE.substring(0,LE.indexOf("_"));
                    }else{
                        writen="FREE_TIME";
                    }
                    Schedule.add(v+")"+writen);
                }
            }
        }
        return Schedule;
    }
}
