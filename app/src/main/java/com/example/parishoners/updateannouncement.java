package com.example.parishoners;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class updateannouncement extends AppCompatActivity {
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Announcements");

    EditText utitle,udes;
    FloatingActionButton update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateannouncement);
        utitle=(EditText) findViewById(R.id.updatetitle);
        udes=(EditText) findViewById(R.id.updatedescription);
        update=(FloatingActionButton) findViewById(R.id.updateannouncementbutton);
        utitle.setText(getIntent().getStringExtra("Title"));
        udes.setText(getIntent().getStringExtra("Body"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newtitle, newbody;
                newtitle = utitle.getText().toString();
                newbody = udes.getText().toString();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Query announcementdatafetch= reference.orderByChild("Title").equalTo(utitle.getText().toString());
//                          announcementdatafetch.getRef().removeValue();
                            announcementdatafetch.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                for(DataSnapshot snapshot1: snapshot.getChildren()){

                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                Toast.makeText(updateannouncement.this, "child has been changed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });
    }
}

