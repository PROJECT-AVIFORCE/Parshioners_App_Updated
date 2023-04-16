package com.example.parishoners;

import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.Map;

public class reportissue extends AppCompatActivity {
    ImageButton back;
Button send;
EditText issue;
FirebaseFirestore fstore;
FirebaseAuth Auth;
String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportissue);
        back=findViewById(R.id.backbtn);
        send =findViewById(R.id.paybtn);
        issue=(EditText) findViewById(R.id.gg);

        fstore = FirebaseFirestore.getInstance();
Auth = FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}