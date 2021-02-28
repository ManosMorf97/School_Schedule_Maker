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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Button Schedule=findViewById(R.id.button6);
        TextView text=findViewById(R.id.textView13);
        EditText etext=findViewById(R.id.editTextTextPersonName10);
        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("Loading...");
                String IQ=etext.getText().toString();
                String sched=Utilities.GO(DataBase.Lessons(),DataBase.Teachers(),IQ);
                File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                try {
                    File myFile = new File(folder, FILENAME);
                    FileOutputStream fstream = new FileOutputStream(myFile);
                    fstream.write(sched.getBytes());
                    fstream.close();
                    text.setText("DONE");
                }catch(IOException e){
                    text.setText("FAILED");
                }
            }
        });
    }
}