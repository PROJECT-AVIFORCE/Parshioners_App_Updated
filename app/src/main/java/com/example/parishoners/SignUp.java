package com.example.parishoners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText email,pass,fname;

    ImageView backBtn,logo;
    Button next,login;
    TextView titletxt,slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        auth = FirebaseAuth.getInstance();
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_btn);
        logo = findViewById(R.id.logo);
        login = findViewById(R.id.login);
        titletxt = findViewById(R.id.title_text);
        slogan = findViewById(R.id.slogan);
        email= findViewById(R.id.Email);
        pass= findViewById(R.id.password);
        fname = findViewById(R.id.name);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString().trim();
                String password = pass.getText().toString();

                if(user.isEmpty()){
                    email.setError("email cannot be empty");
                }
                if (password.isEmpty()){
                    pass.setError("wrong password");
                } else{
                    auth.createUserWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUp.this,"signup Successful",Toast.LENGTH_SHORT).show();
                                startActivity( new Intent(SignUp.this,SignUp2ndClass.class));
                            } else {
                                Toast.makeText(SignUp.this, "Signup failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    public void callbacktologinscreen(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    }

    public void callNextSignupScreen(View view){
        Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);



        //Add transition
        Pair[]pairs = new Pair[6];

        pairs[0]= new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1]= new Pair<View,String>(next,"transition_next_btn");
        pairs[2]= new Pair<View,String>(logo,"transition_logo_image");
        pairs[3]= new Pair<View,String>(login,"transition_login_btn");
        pairs[4]= new Pair<View,String>(titletxt,"transition_title_text");
        pairs[5]= new Pair<View,String>(slogan,"transition_slogan_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this,pairs);
        startActivity(intent,options.toBundle());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp.super.onBackPressed();
            }
        });
    }


}