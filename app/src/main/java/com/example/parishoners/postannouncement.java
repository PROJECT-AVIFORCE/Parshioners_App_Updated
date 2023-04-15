package com.example.parishoners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class postannouncement extends AppCompatActivity {



    RecyclerView announcementview;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;

    ArrayList<announcementclass> arrayList;
    announcementadapter adapter;
    FloatingActionButton post;
    String UserID ;
    FirebaseAuth auth;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postannouncement);

        back=findViewById(R.id.backmenu_icon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        announcementview=(RecyclerView) findViewById(R.id.allannouncements);
        announcementview.setHasFixedSize(true);
        announcementview.setLayoutManager(new LinearLayoutManager(this));
        myRef=database.getReference("Announcements");
        arrayList=new ArrayList<>();
        adapter=new announcementadapter(getApplicationContext(),arrayList);
        Eventchangelistener();
        adapter.notifyDataSetChanged();
        announcementview.setAdapter(adapter);
        post=findViewById(R.id.newannouncementbutton);
        //admin key
        // String  adminID=
        //admin checker
        auth = FirebaseAuth.getInstance();
        UserID = Objects.requireNonNull(auth.getCurrentUser()).getUid();

        if( UserID.equals("kaAwxZeCUDQpB421qKc5ArGfXci2")){

            post.setVisibility(View.VISIBLE);
            post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getApplicationContext(),newannouncement.class);
                    startActivity(intent);
                }
            });

    }else
        {
            post.setVisibility(View.INVISIBLE);

           }
    }

    private void Eventchangelistener() {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    announcementclass announcementclass=dataSnapshot.getValue(announcementclass.class);
                    arrayList.add(announcementclass);
                    dataSnapshot.getKey();
                }
                adapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}