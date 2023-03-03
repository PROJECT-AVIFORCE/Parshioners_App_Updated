package com.example.parishoners;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Utility {

    static CollectionReference getallcollectionreferences(){

        return FirebaseFirestore.getInstance().collection("announcements").document()
                .collection("newannouncement");



    }
}
