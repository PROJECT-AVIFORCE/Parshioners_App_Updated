package com.example.parishoners;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class adddues extends AppCompatActivity {

    EditText date1,duetitle,duetime,duedesc;
    FloatingActionButton adddues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddues);
        date1=(EditText)findViewById(R.id.duedate);
        duetitle=(EditText)findViewById(R.id.duestitle);
        duetime=(EditText)findViewById(R.id.duetime);
        duedesc=(EditText)findViewById(R.id.duesdescription);
        adddues=(FloatingActionButton)findViewById(R.id.addduebutton);




        adddues.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(validatefields()){
                      Map<String,String> dues=new HashMap<>();
                      dues.put("Title",duetitle.getText().toString());
                      dues.put("Description",duedesc.getText().toString());
                      dues.put("Date",date1.getText().toString());
                      dues.put("Time",duetime.getText().toString());

                      FirebaseFirestore.getInstance().collection("Dues").add(dues).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                          @Override
                          public void onComplete(@NonNull Task<DocumentReference> task) {
                              if(task.isSuccessful()){
                                  Toast.makeText(adddues.this, "data inserted", Toast.LENGTH_SHORT).show();
                                  finish();

                              }
                              else {
                                  Toast.makeText(adddues.this, "failed to insert data", Toast.LENGTH_SHORT).show();
                              }

                          }
                      });


                  }
              }
          });




    }

    private boolean validatefields() {
        String dtitle=duetitle.getText().toString();
        String dtime=duetime.getText().toString();
        String ddate=date1.getText().toString();
        String ddes=duedesc.getText().toString();
        boolean v=true;
        if(dtitle.length()==0){
            Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
            v=false;
        }
            if(dtime.length()==0){
                Toast.makeText(this, "time cannot be empty", Toast.LENGTH_SHORT).show();
                v=false;

            }
                if(ddate.length()==0){
                Toast.makeText(this, "Date cannot be empty", Toast.LENGTH_SHORT).show();
                   v =false;
                }
                    if(ddes.length()==0){
                Toast.makeText(this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
                         v=false;
                    }



                        return v;


    }
}