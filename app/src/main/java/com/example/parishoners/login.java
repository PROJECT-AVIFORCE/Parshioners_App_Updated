package com.example.parishoners;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText Username;
    EditText Password;
    FirebaseAuth authi;
    ProgressDialog dialog;

String adminemail="vipulfadte43@gmail.com" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.editTextTextEmailAddress3);
        Password = findViewById(R.id.pass);
        Button submit=findViewById(R.id.submit);
        authi= FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = Username.getText().toString().trim();
                String password = Password.getText().toString();

               if(!email.equals(adminemail)) {
                    Username.setError("NOT ADMIN");
                   startActivity(new Intent(login.this ,MainActivity.class));
                   finish();
                   Toast.makeText(login.this, "your not an admin", Toast.LENGTH_SHORT).show();
                }
        //change from here
                else if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if (!password.isEmpty()){
                        dialog=new ProgressDialog(login.this);
                        dialog.setTitle("Please Wait");
                        dialog.setMessage("checking data");
                        dialog.show();
                        authi.signInWithEmailAndPassword(email,password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {

                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        dialog.dismiss();
                                        Toast.makeText(login.this,"WELCOME ADMIN",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(login.this ,Dashboard.class));
                                        finish();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialog.dismiss();
                                        Toast.makeText(login.this, "Incorrect Email or Password.Please Try Again.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }else{
                        Password.setError("Password cannot be empty");
                    }
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