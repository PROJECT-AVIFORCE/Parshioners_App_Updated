package com.example.parishoners;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserProfile extends AppCompatActivity {
    FirebaseAuth auth;
    EditText  titlename ,age,gender ,phone ,address;
    TextView Fullname , titleEmail,agetext,gendertext,phonetext,adresstext;
    FirebaseFirestore fstore;
    Button logout, updatebtn;
    String UserID;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
          auth = FirebaseAuth.getInstance();
        logout =findViewById(R.id.logout);
        updatebtn=findViewById(R.id.update);
                fstore = FirebaseFirestore.getInstance();


         titlename  =findViewById(R.id.name);
         age = findViewById(R.id.ageid);
         gender = findViewById(R.id.sex);
         phone = findViewById(R.id.phoneid);
         address = findViewById(R.id.emailid);
         Fullname =findViewById(R.id.fullname);
         titleEmail =findViewById(R.id.iduu);
         agetext =findViewById(R.id.age);
         gendertext =findViewById(R.id.gender);
         phonetext =findViewById(R.id.phone);
         adresstext =findViewById(R.id.adrees);




        //retrive data

        UserID = auth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("users").document(UserID);
        documentReference.addSnapshotListener(UserProfile.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                titlename.setText(value.getString("name"));
                age.setText(value.getString("dob"));
                gender.setText(value.getString("gender"));
                phone.setText(value.getString("phno"));
                address.setText(value.getString("address"));




//            profile card
                Fullname.setText(value.getString("name"));
                titleEmail.setText(value.getString("Email"));
                phonetext.setText(value.getString("phno"));
                adresstext.setText(value.getString("address"));
                gendertext.setText(value.getString("gender"));
                agetext.setText(value.getString("dob"));


            }
        });



        updatebtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

                DocumentReference documentReference = fstore.collection("users").document(UserID);

String name1  = titlename.getText().toString();
        String age1  = age.getText().toString();
        String gender1  = gender.getText().toString();
        String phone1  = phone.getText().toString();
        String famid  = address.getText().toString();

        documentReference.update("name",name1,"dob",age1,"gender",gender1,"phno",phone1,"address",famid);


    }
});

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UserProfile.this, MainActivity.class));

                auth.signOut();
                finish();
            }
        });
    }
}
