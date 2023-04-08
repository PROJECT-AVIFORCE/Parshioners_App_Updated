package com.example.parishoners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    EditText Username;
    EditText Password;
    FirebaseDatabase database;
    DatabaseReference reference;
    private FirebaseAuth authi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Username = findViewById(R.id.user);
        Password = findViewById(R.id.pass);
        Button submit=findViewById(R.id.submit);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("admin");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = Username.getText().toString();
                String password = Password.getText().toString();
//change from here
                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if (!password.isEmpty()){

                        authi.signInWithEmailAndPassword(email,password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(login.this,"login successful",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(login.this ,Dashboard.class));
                                        finish();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(login.this, "Incorrect Email or Password.Please Try Again.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }else{
                        Password.setError("Password cannot be empty");
                    }
                }else if(email.isEmpty()) {
                    Username.setError("Email cannot be empty");
                }else if(password.isEmpty()) {
                    Username.setError("Password cannot be empty");
                }else {
                    Username.setError("please enter valid email");
                }
            }


        });
//till here

//               if (username.equals("admin") && password.equals("11111")) {
//                    Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
//                    Intent i=new Intent(login.this, Dashboard.class);
//                    startActivity(i);
//
//                } else {
//                    Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    }
}