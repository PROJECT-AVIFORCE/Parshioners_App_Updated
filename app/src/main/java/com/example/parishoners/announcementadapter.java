package com.example.parishoners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class announcementadapter extends RecyclerView.Adapter<announcementadapter.announcementviewholder>
{

    Context context;
    List<announcementclass> announcements;

    public announcementadapter(Context context, List<announcementclass> announcements) {
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
        announcementclass announcementclass=announcements.get(position);
        holder.title.setText(announcementclass.getTitles());
        holder.date.setText(announcementclass.getDate());
        holder.des.setText(announcementclass.getDes());
        holder.time.setText(announcementclass.getTime());

    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }

    public static class announcementviewholder extends RecyclerView.ViewHolder{

        TextView title,des,date,time;


        public announcementviewholder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.announcement_title);
            des=itemView.findViewById(R.id.announcement_desc);
            date=itemView.findViewById(R.id.announcement_date);
            time=itemView.findViewById(R.id.announcement_time);
        }
    }
}
