package com.example.parishoners;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class postannouncement extends AppCompatActivity {
    RecyclerView announcementview;
    FirebaseFirestore db;
    List<announcementclass> arrayList;
    announcementadapter adapter;
    FloatingActionButton post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postannouncement);
        announcementview=(RecyclerView) findViewById(R.id.allannouncements);
        announcementview.setHasFixedSize(true);
        announcementview.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();

        arrayList=new ArrayList<announcementclass>();
        adapter=new announcementadapter(this,arrayList);
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
        db.collection("announcements").orderBy("date", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error!=null){
                    Log.e("firestore error",error.getMessage());
                    return;
                }

                for(DocumentChange documentChange :value.getDocumentChanges()){
                    if(documentChange.getType()==DocumentChange.Type.ADDED||
                            documentChange.getType()==DocumentChange.Type.MODIFIED||
                            documentChange.getType()==DocumentChange.Type.REMOVED
                    )
                    {

                        arrayList.add(documentChange.getDocument().toObject(announcementclass.class));

                    }
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }
}