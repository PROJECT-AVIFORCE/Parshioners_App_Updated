package com.example.parishoners;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Objects;

public class announcementadapter extends RecyclerView.Adapter<announcementadapter.announcementviewholder>
{

    Context context;
    ArrayList<announcementclass> announcements;

    public announcementadapter(Context context, ArrayList<announcementclass> announcements) {
        this.context = context;
        this.announcements = announcements;

    }

    @NonNull
    @Override
    public announcementadapter.announcementviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(context).inflate(R.layout.adminannouncementtask,parent,false);

        return new announcementviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull announcementadapter.announcementviewholder holder, int position) {
        announcementclass announcementclass= announcements.get(position);
        holder.Title.setText(announcementclass.getTitle());
        holder.Date.setText(announcementclass.getDate());
        holder.Body.setText(announcementclass.getBody());
        holder.Time.setText(announcementclass.getTime());
        String UserID;
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        UserID = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        if( UserID.equals("kaAwxZeCUDQpB421qKc5ArGfXci2")){
            holder.edit.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.VISIBLE);

            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), updateannouncement.class);
                    i.putExtra("Title", announcementclass.getTitle());
                    i.putExtra("Body", announcementclass.getBody());
                    i.putExtra("Time", announcementclass.getTime());
                    i.putExtra("Date", announcementclass.getDate());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(i);


                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Announcements");
                    Query q = reference.orderByChild("Title").equalTo(announcementclass.getTitle());
                    q.getRef().setValue(null)
//                        reference.setValue(null)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Data Deleted Successfully Please go back to see the magic", Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            });
            //cardview click
            holder.announcement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        else {
            holder.edit.setVisibility(View.GONE);
            holder.delete.setVisibility(View.GONE);
        }




    }


    @Override
    public int getItemCount() {

        return announcements.size();
    }

    public static class announcementviewholder extends RecyclerView.ViewHolder{

        TextView Title,Body,Date,Time;
        CardView announcement;
        ImageView edit,delete;



        public announcementviewholder(@NonNull View itemView) {
            super(itemView);
            edit=itemView.findViewById(R.id.editannouncement);
            delete=itemView.findViewById(R.id.deleteannouncement);
            Title=itemView.findViewById(R.id.atitle);
            Body=itemView.findViewById(R.id.adescription);
            Date=itemView.findViewById(R.id.aannouncement_date);
            Time=itemView.findViewById(R.id.aannouncement_time);
            announcement=itemView.findViewById(R.id.announcement_acardview);
        }
    }
}
