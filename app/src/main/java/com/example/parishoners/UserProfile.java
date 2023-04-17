package com.example.parishoners;



import android.app.Dialog;
import android.content.Intent;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;



import com.google.firebase.auth.FirebaseAuth;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {


    FirebaseAuth auth;
    EditText  titlename ,age,gender ,phone ,address;
    TextView Fullname , titleEmail,agetext,gendertext,phonetext,adresstext;
    FirebaseStorage storage;
    FirebaseFirestore fstore;
    Button logout, updatebtn;
    CircleImageView pfp;
    Uri filePath;


    private Dialog dialog;


    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        auth = FirebaseAuth.getInstance();
        logout =findViewById(R.id.logout);
        updatebtn=findViewById(R.id.update);
        fstore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

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
        pfp = findViewById(R.id.profile_image);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


     String   UserID = auth.getCurrentUser().getUid();
        //retrive data






        DocumentReference documentReference = fstore.collection("users").document(UserID);
        documentReference.addSnapshotListener(UserProfile.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                titlename.setText(value.getString("name"));
                age.setText(value.getString("dob"));
                gender.setText(value.getString("gender"));
                phone.setText(value.getString("phno"));
                address.setText(value.getString("address"));

//image

                filePath =Uri.parse("https://firebasestorage.googleapis.com/v0/b/backendlogsign.appspot.com/o/"+UserID+"?alt=media&token=a256ed91-52a1-4f61-b9dd-d917d046a19a");
                Picasso.get()
                        .load(filePath)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .into(pfp);

//            profile card
                Fullname.setText(value.getString("name"));
                titleEmail.setText(value.getString("Email"));
                phonetext.setText(value.getString("phno"));
                adresstext.setText(value.getString("address"));
                gendertext.setText(value.getString("gender"));
                agetext.setText(value.getString("dob"));


            }
        });

        pfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(UserProfile.this , profile_pic.class);
                startActivity(i);
       finish();
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
                //Create the Dialog here
                dialog = new Dialog(v.getContext());
                dialog.show();
                dialog.setContentView(R.layout.custom_dialog_layout);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
                }
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false); //Optional
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

                Button Yes = dialog.findViewById(R.id.btn_yes);
                Button No = dialog.findViewById(R.id.btn_no);

                Yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        auth.signOut();
                        startActivity(new Intent(UserProfile.this, MainActivity.class));
                        finish();

                    }
                });
                No.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }
//






}
