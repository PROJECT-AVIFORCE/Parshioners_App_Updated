package com.example.parishoners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class dues extends AppCompatActivity {
    FloatingActionButton adddue;
//    ProgressDialog dialog=new ProgressDialog(this);
    RecyclerView recyclerView;
    ArrayList<Duesdataclass> arrayList;
    DuesAdapter duesAdapter;
    FirebaseFirestore db;
    ImageView back;
    String UserID ;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dues);
//        dialog.setCancelable(false);
//        dialog.setMessage("Fetching dues");
//        dialog.show();
        back=findViewById(R.id.duesback_icon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView=findViewById(R.id.alldues);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db=FirebaseFirestore.getInstance();
        //define arraylist
        arrayList=new ArrayList<Duesdataclass>();
        duesAdapter=new DuesAdapter(this,arrayList);
        recyclerView.setAdapter(duesAdapter);

        //method for changes in recycler
        Eventchange();



       adddue=(FloatingActionButton) findViewById(R.id.adddues);

        auth = FirebaseAuth.getInstance();
        UserID = Objects.requireNonNull(auth.getCurrentUser()).getUid();

        if( UserID.equals("kaAwxZeCUDQpB421qKc5ArGfXci2")){

            adddue.setVisibility(View.VISIBLE);
            adddue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),adddues.class);
                    startActivity(intent);
                }
            });

        }else
        {
            adddue.setVisibility(View.INVISIBLE);

        }




    }

    private void Eventchange() {
        db.collection("Dues").orderBy("Date", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Toast.makeText(dues.this, "firestore error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
               for (DocumentChange d:value.getDocumentChanges()){
                   arrayList.add(d.getDocument().toObject(Duesdataclass.class));
               }
               duesAdapter.notifyDataSetChanged();
            }
        });
    }
}