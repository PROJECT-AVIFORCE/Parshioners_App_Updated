package com.example.parishoners;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
        v= LayoutInflater.from(context).inflate(R.layout.announcementitem,parent,false);

        return new announcementviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull announcementadapter.announcementviewholder holder, int position) {
        announcementclass announcementclass= announcements.get(position);
        holder.Title.setText(announcementclass.getTitle());
        holder.Date.setText(announcementclass.getDate());
        holder.Body.setText(announcementclass.getBody());
        holder.Time.setText(announcementclass.getTime());

        holder.announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to update announcement class
                Intent i=new Intent(v.getContext(),updateannouncement.class);
                i.putExtra("Title",announcementclass.getTitle());
                i.putExtra("Body",announcementclass.getBody());
                i.putExtra("Time",announcementclass.getTime());
                i.putExtra("Date",announcementclass.getDate());

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(i);


            }
        });




    }

    @Override
    public int getItemCount() {

        return announcements.size();
    }

    public static class announcementviewholder extends RecyclerView.ViewHolder{

        TextView Title,Body,Date,Time;
        public CardView announcement;



        public announcementviewholder(@NonNull View itemView) {
            super(itemView);

            Title=itemView.findViewById(R.id.announcement_title);
            Body=itemView.findViewById(R.id.announcement_desc);
            Date=itemView.findViewById(R.id.announcement_date);
            Time=itemView.findViewById(R.id.announcement_time);
            announcement=itemView.findViewById(R.id.announcement_cardview);
        }
    }
}
