package com.example.parishoners;


import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class UserProfile extends AppCompatActivity {
    FirebaseAuth auth;
    EditText  titlename ,age,gender ,phone ,email;
    TextView Fullname ,family;
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
        email = findViewById(R.id.emailid);
Fullname =findViewById(R.id.fullname);
        family =findViewById(R.id.iduu);
        //retrive data

        UserID = auth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("users").document(UserID);
        documentReference.addSnapshotListener(UserProfile.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                titlename.setText(value.getString("name"));
                Fullname.setText(value.getString("name"));
                age.setText(value.getString("dob"));
                gender.setText(value.getString("gender"));
                phone.setText(value.getString("phno"));
                email.setText(value.getString("address"));
                family.setText(value.getString("FamilyID"));

               // auth = FirebaseAuth.getInstance();
            }
        });


updatebtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

                DocumentReference documentReference = fstore.collection("users").document(UserID);


      // documentReference.update("name","vipul fadte");



    }
});

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.signOut();
                startActivity(new Intent(UserProfile.this, MainActivity.class));
                finish();// ...


            }
        });
    }
}