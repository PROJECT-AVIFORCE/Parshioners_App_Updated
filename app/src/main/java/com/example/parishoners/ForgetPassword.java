package com.example.parishoners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgetPassword extends AppCompatActivity {

    FirebaseAuth auth;
private EditText email;
private Button send;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        auth =FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextTextEmailAddress);
        send = findViewById(R.id.sendid);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Useremail = email.getText().toString();


                if(TextUtils.isEmpty(Useremail)&& !Patterns.EMAIL_ADDRESS.matcher(Useremail).matches()){
                    Toast.makeText(ForgetPassword.this, "check your email", Toast.LENGTH_SHORT).show();
                return;
                }
                auth.sendPasswordResetEmail(Useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgetPassword.this, "Check your email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ForgetPassword.this, "unable to send", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}