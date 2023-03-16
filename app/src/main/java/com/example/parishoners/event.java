package com.example.parishoners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class event extends AppCompatActivity {

    RecyclerView eventrecycler;
    FirebaseDatabase eventdb=FirebaseDatabase.getInstance();
    DatabaseReference root=eventdb.getReference().child("events");
    eventadapter eventsadapter;
    ArrayList<eventclass> eventslist;
    Button event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        event=(Button) findViewById(R.id.addTask);
        eventrecycler=findViewById(R.id.taskRecycler);
        eventrecycler.setHasFixedSize(true);
        eventrecycler.setLayoutManager(new LinearLayoutManager(this));
        eventslist= new ArrayList<>();
        eventsadapter=new eventadapter(this,eventslist);
        eventrecycler.setAdapter(eventsadapter);


        //fetch data from database
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //run loop
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    eventclass eventclass = dataSnapshot.getValue(eventclass.class);
                    eventslist.add(eventclass);
                }
                eventsadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),addevent.class);
                startActivity(i);
            }
        });


    }
}