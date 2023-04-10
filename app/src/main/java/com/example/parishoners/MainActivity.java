package com.example.parishoners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth authi;
    EditText loginemail,lgpass;
    Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        authi = FirebaseAuth.getInstance();
        Button admin=findViewById(R.id.admin);
        Button signup=findViewById(R.id.signup);
        Button forget=findViewById(R.id.forget_password);
        loginemail = findViewById(R.id.editTextTextEmailAddress3);
        lgpass = findViewById(R.id.editTextTextPassword);
        loginbtn = findViewById(R.id.submit);

        //user already logged in code
        if (authi.getCurrentUser() != null) {

          Intent intent = new Intent(MainActivity.this, Dashboard.class);
           startActivity(intent);
           finish();
       }
        //till here



        loginbtn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =loginemail.getText().toString();
                String pass =lgpass.getText().toString();

                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if (!pass.isEmpty()){

                        authi.signInWithEmailAndPassword(email,pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(MainActivity.this,"login successful",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this ,Dashboard.class));
                                        finish();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MainActivity.this, "Incorrect Email or Password.Please Try Again.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }else{
                        lgpass.setError("Password cannot be empty");
                    }
                }else if(email.isEmpty()) {
                    loginemail.setError("Email cannot be empty");
                }else if(pass.isEmpty()) {
                    loginemail.setError("Password cannot be empty");
                }else {
                    loginemail.setError("please enter valid email");
                }
            }


        });



        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, login.class);
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(i);
            }
        });


    }
}