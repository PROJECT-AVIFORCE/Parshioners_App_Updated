package com.example.parishoners;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class newannouncement extends AppCompatActivity {
    ArrayList<announcementclass> arrayList;
    EditText title,des;
    FloatingActionButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newannouncement);
        b1=(FloatingActionButton) findViewById(R.id.saveannouncementbutton);
        title = (EditText)findViewById(R.id.newtitle);
        des=(EditText) findViewById(R.id.newdescription);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(title.getText().toString().isEmpty()||des.getText().toString().isEmpty()){
                    Toast.makeText(newannouncement.this, "FIELDS CANNOT BE EMPTY", Toast.LENGTH_SHORT).show();

                }
                else {
                    String t = title.getText().toString();
                    String desc = des.getText().toString();

                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    DatabaseReference root=db.getReference().child("Announcements");

                    //date format
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date1= new Date();
                    String strDate = dateFormat.format(date1).toString();

                    //add time format
                    DateFormat timeformat = new SimpleDateFormat("hh:mm a");
                    Date time= new Date();
                    String strtime = timeformat.format(time).toString();

                    HashMap map=new HashMap<>();
                    map.put("Title",t);
                    map.put("Body",desc);
                    map.put("Date",strDate);
                    map.put("Time",strtime);
                    root.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(newannouncement.this, "data has been inserted", Toast.LENGTH_SHORT).show();
                            announcementclass ann=new announcementclass();
                            ann.setTitle(t);
                            ann.setBody(desc);
                            ann.setDate(strDate);
                            //add time to announcement class
                            ann.setTime(strtime);
                             //arrayList.add(ann);
                            finish();
                            Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(intent);


                        }
                    });



                }
            }


        });

    }
}