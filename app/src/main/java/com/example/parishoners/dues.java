package com.example.parishoners;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class dues extends AppCompatActivity {
    FloatingActionButton adddue;
//    ProgressDialog dialog=new ProgressDialog(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dues);
//        dialog.setCancelable(false);
//        dialog.setMessage("Fetching dues");
//        dialog.show();
//          FirebaseFirestore.getInstance().collection("Dues").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                for(DocumentChange d:value.getDocumentChanges()){
//
//                }
//
//            }
//        });
       adddue=(FloatingActionButton) findViewById(R.id.adddues);
       adddue.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(),adddues.class);
               startActivity(intent);
           }
       });


    }
}