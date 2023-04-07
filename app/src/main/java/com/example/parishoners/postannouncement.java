package com.example.parishoners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class postannouncement extends AppCompatActivity {


    RecyclerView announcementview;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;

    ArrayList<announcementclass> arrayList;
    announcementadapter adapter;
    FloatingActionButton post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postannouncement);
        announcementview=(RecyclerView) findViewById(R.id.allannouncements);
        announcementview.setHasFixedSize(true);
        announcementview.setLayoutManager(new LinearLayoutManager(this));

        myRef=database.getReference("Announcements");
        arrayList=new ArrayList<>();
        adapter=new announcementadapter(getApplicationContext(),arrayList);
        announcementview.setAdapter(adapter);

        Eventchangelistener();



        post=findViewById(R.id.newannouncementbutton);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),newannouncement.class);
                startActivity(intent);
            }
        });

    }

    private void Eventchangelistener() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    announcementclass announcementclass=dataSnapshot.getValue(announcementclass.class);
                    arrayList.add(announcementclass);
//                    dataSnapshot.getKey();

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}