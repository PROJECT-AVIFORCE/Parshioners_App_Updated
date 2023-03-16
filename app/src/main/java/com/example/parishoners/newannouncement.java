package com.example.parishoners;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class newannouncement extends AppCompatActivity {
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
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    announcementclass ann=new announcementclass();
                    //date format
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date1= new Date();
                    String strDate = dateFormat.format(date1).toString();
                    ann.setTitles(t);
                    ann.setDes(desc);
                    ann.setDate(strDate);
                    //add time format
                    DateFormat timeformat = new SimpleDateFormat("hh:mm a");
                    Date time= new Date();
                    String strtime = timeformat.format(time).toString();
                    ann.setTime(strtime);
                    db.collection("announcements").
                            add(ann).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Toast.makeText(newannouncement.this, "data has been inserted", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                    Toast.makeText(newannouncement.this, "Failed to add data", Toast.LENGTH_SHORT).show();
                                }
                            });


                }
            }


        });

    }
}