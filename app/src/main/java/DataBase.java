import java.util.ArrayList;

public class DataBase {
    private static ArrayList<Lesson> lessons=new ArrayList<Lesson>();
    private static ArrayList<Teacher> teachers=new ArrayList<Teacher>();
    public static ArrayList<Lesson> Lessons(){
        return lessons;
    }
    public static ArrayList<Teacher> Teachers(){
        return teachers;
    }
}
