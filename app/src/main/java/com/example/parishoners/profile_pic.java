package com.example.parishoners;





import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;

import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;


import de.hdodenhof.circleimageview.CircleImageView;


public class profile_pic extends AppCompatActivity {

        // views for button
        Button btnSelect, btnUpload ;

 String UserID;
        // view for image view
        private CircleImageView imageView;

        // Uri indicates, where the image will be picked from

     Uri filePath;

        FirebaseAuth auth;

        FirebaseFirestore fstore;

        // request code
        private final int PICK_IMAGE_REQUEST = 22;

        // instance for firebase storage and StorageReference
        FirebaseStorage storage;
        StorageReference storageReference;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile_pic);

            auth =  FirebaseAuth.getInstance();
            fstore = FirebaseFirestore.getInstance();
            UserID= auth.getCurrentUser().getUid();
            // initialise views
            btnSelect = findViewById(R.id.close);
            btnUpload = findViewById(R.id.update);
            imageView = findViewById(R.id.profileimage);


            // get the Firebase storage reference
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();


            // on pressing btnSelect SelectImage() is called
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    SelectImage();
                }
            });

            // on pressing btnUpload uploadImage() is called
            btnUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    uploadImage();
                }
            });

            btnSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

Intent i = new Intent(profile_pic.this,UserProfile.class);
startActivity(i);
finish();
                }
            });
        }



    // Select Image method
    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                imageView.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void uploadImage()
    {
        if (filePath != null) {
            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            UserID);

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    Toast
                                            .makeText(profile_pic.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                    Intent i = new Intent(profile_pic.this,UserProfile.class);
                                    startActivity(i);
                                    finish();

                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            Toast
                                    .makeText(profile_pic.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });

        }
        else{

            Toast.makeText(this, "Click on image to set", Toast.LENGTH_SHORT).show();
        }
    }
}