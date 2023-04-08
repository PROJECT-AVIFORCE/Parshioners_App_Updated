package com.example.parishoners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
 
public class profile extends AppCompatActivity {

     FirebaseAuth auth;
    EditText titlename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();
        Button logout =findViewById(R.id.logoutbtn);

        showuserdata();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(profile.this, MainActivity.class);

                startActivity(intent);

            }
        });


    }

    public void showuserdata(){

        Intent intent =getIntent();
        String name = intent.getStringExtra("name");

        titlename.setText(name);
    }
}