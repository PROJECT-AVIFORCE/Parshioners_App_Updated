package com.example.parishoners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUp3rdClass extends AppCompatActivity {
    Button login, next;
    EditText ph_no;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3rd_class);
        next=findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.login);
        backBtn = findViewById(R.id.signup_back_button);
       ph_no = findViewById(R.id.phoneNO);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


       PhoneAuthProvider.getInstance().verifyPhoneNumber(
               "+919579898209",20,
               TimeUnit.SECONDS,
               SignUp3rdClass.this,
               new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                   @Override
                   public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                       Toast.makeText(SignUp3rdClass.this, "done", Toast.LENGTH_SHORT).show();

                   }

                   @Override
                   public void onVerificationFailed(@NonNull FirebaseException e) {
                       Toast.makeText(SignUp3rdClass.this, "retry", Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                       super.onCodeSent(verificationId, forceResendingToken);

                       Intent i = new Intent(SignUp3rdClass.this, OTP.class);
                       startActivity(i);
                       Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();

                   }
               }
               );

                }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp3rdClass.this, SignUp2ndClass.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Successfully signed up!", Toast.LENGTH_SHORT).show();

            }
        });

           }


}