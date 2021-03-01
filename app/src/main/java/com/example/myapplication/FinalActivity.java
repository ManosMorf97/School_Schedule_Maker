package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Button Schedule=findViewById(R.id.button6);
        TextView text=findViewById(R.id.textView12);
        EditText etext=findViewById(R.id.editTextTextPersonName10);

        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String IQ=etext.getText().toString();
                WriteToFile(IQ);
               /* try {
                    FileWriter fileWriter = new FileWriter("schedule.txt");
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    bufferedWriter.write(sched);
                    text.setText("DONE");
                }catch (IOException e){
                    text.setText("FAILED");
                }*/
            }
        });
    }
    public void WriteToFile(String IQ){
        String sched=Utilities.GO(DataBase.Lessons(),DataBase.Teachers(),IQ);
        FileOutputStream fos=null;
        try {
            fos=openFileOutput("schedule.txt",MODE_PRIVATE);
            fos.write(sched.getBytes());
            Toast.makeText(this,"Saved to "+getFilesDir()+"/schedule.txt",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}