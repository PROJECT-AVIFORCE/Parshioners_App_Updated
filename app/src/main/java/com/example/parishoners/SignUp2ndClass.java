package com.example.parishoners;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//import java.util.Calendar;

public class SignUp2ndClass extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    ImageView backBtn,logo;
    Button next,login;
    TextView titletxt,slogan;
   RadioGroup radio;
    RadioButton gen;
    FirebaseFirestore fstore;
    FirebaseAuth auth;
    String UserID;
    EditText phone, age;
String TAG="TAG";
    //DatePicker datePicker;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd_class);

        //Hooks
        auth = FirebaseAuth.getInstance();
        backBtn = findViewById(R.id.signup2_back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        next = findViewById(R.id.signup_next_btn);
        logo = findViewById(R.id.logo);

        titletxt = findViewById(R.id.title_text);
        slogan = findViewById(R.id.slogan);
        radio=findViewById(R.id.rg);
        phone = findViewById(R.id.phoneNO);
        age = findViewById(R.id.ageid);
        fstore = FirebaseFirestore.getInstance();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                int ID=radio.getCheckedRadioButtonId();

                gen =findViewById(ID);


                String gender = gen.getText().toString();
                String dob = age.getText().toString();
                String Phone = phone.getText().toString();


                String UserEmail = getIntent().getStringExtra("emailkey");
                String name = getIntent().getStringExtra("keyname");
                String password = getIntent().getStringExtra("keypass");
                String famid = getIntent().getStringExtra("keyfid");



                auth.createUserWithEmailAndPassword(UserEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp2ndClass.this,"WELCOME "+name.toUpperCase(),Toast.LENGTH_SHORT).show();

                            UserID= auth.getCurrentUser().getUid();

                            DocumentReference documentReference = fstore.collection("users").document(UserID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("name",name);
                            user.put("address",famid);
                            user.put("Email",UserEmail);
                            user.put("gender",gender);
                           user.put("phno",Phone);
                            user.put("dob",dob);


                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: DATA STORAGE ");                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUp2ndClass.this, "failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        } else {
                            Toast.makeText(SignUp2ndClass.this, "Signup failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }





   public void callNextSignupScreen(View view){
        Intent intent = new Intent(getApplicationContext(), SignUp3rdClass.class);

        //Add transition
        Pair[]pairs = new Pair[6];

        pairs[0]= new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1]= new Pair<View,String>(next,"transition_next_btn");
        pairs[2]= new Pair<View,String>(logo,"transition_logo_image");
        pairs[3]= new Pair<View,String>(login,"transition_login_btn");
        pairs[4]= new Pair<View,String>(titletxt,"transition_title_text");
        pairs[5]= new Pair<View,String>(slogan,"transition_slogan_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this,pairs);
        startActivity(intent,options.toBundle());


    }

}
