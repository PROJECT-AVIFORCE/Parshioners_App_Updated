package com.example.parishoners;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class updateannouncement extends AppCompatActivity {


    DatabaseReference root= FirebaseDatabase.getInstance().getReference("Announcements");
    EditText utitle,udescription;
    FloatingActionButton update;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateannouncement);
        utitle=(EditText) findViewById(R.id.newUtitle);
        udescription=(EditText) findViewById(R.id.newUdescription);
        utitle.setText(getIntent().getExtras().getString("Title"));
        udescription.setText(getIntent().getExtras().getString("Body"));


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String u=utitle.getText().toString();
                Query checkkey=root.orderByChild("Title").equalTo(u);

                if(snapshot.exists()){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        update=(FloatingActionButton)findViewById(R.id.updateannouncementbutton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatetitle = utitle.getText().toString();
                String updatedes = udescription.getText().toString();
                String time1 = (String) getIntent().getExtras().get("Time");
                String date1 = (String) getIntent().getExtras().get("Date");

                updatedata(updatetitle,updatedes,time1,date1);





            }
        });
            }

    private void updatedata(String updatetitle, String updatedes, String time1, String date1) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("Title", updatetitle);
        map.put("Body", updatedes);
        map.put("Time", time1);
        map.put("Date", date1);

        root.child(updatetitle).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(updateannouncement.this, "Data Updated ", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(updateannouncement.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }
}

