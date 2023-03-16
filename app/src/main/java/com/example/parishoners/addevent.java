package com.example.parishoners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class addevent extends AppCompatActivity {
    EditText etitle,edes,edate,etime;
    Button addevent;
    private FirebaseDatabase eventdb=FirebaseDatabase.getInstance();
    private DatabaseReference root=eventdb.getReference().child("events");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);

        etitle=(EditText) findViewById(R.id.addTaskTitle);
        edes=(EditText) findViewById(R.id.addTaskDescription);
        edate=(EditText) findViewById(R.id.taskDate);
        etime=(EditText) findViewById(R.id.taskTime);
        addevent=(Button) findViewById(R.id.addTask);
        addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatefields();
                if(validatefields())
                {
                    String title,description,dates,time;
                    title=etitle.getText().toString();
                    description=edes.getText().toString();
                    dates=edate.getText().toString();
                    time=etime.getText().toString();
                    //Date format
                    //insert the data using hashmap
                    HashMap<String,String> evenetmap=new HashMap<>();
                    evenetmap.put("Title",title);
                    evenetmap.put("Description",description);
                    evenetmap.put("Date",dates);
                    evenetmap.put("Time",time);
                    root.push().setValue(evenetmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(addevent.this, "data has been inserted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });

                }
                else
                {
                    Toast.makeText(addevent.this, "data cannot be inserted", Toast.LENGTH_SHORT).show();
                }
                Intent i=new Intent();


            }
        });


    }

    private boolean validatefields() {
        if(etitle.length()==0){
            etitle.setError("title cannot be empty");
            return false;

        }
        if(edes.length()==0){
            edes.setError("description cannot be empty");
            return false;
        }
        if(edate.length()==0){
            edes.setError("date value cannot be empty");
            return false;
        }
        if(etime.length()==0){
            edes.setError("time is required ");
            return false;
        }
        return true;
    }


}