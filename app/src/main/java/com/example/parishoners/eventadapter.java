package com.example.parishoners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class eventadapter extends RecyclerView.Adapter<eventadapter.Viewholder> {
    Context context;
    ArrayList<eventclass> list;

    public eventadapter(Context context, ArrayList<eventclass> eventslist) {
        this.context=context;
        this.list=eventslist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_task,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        eventclass eventclass=list.get(position);
        holder.title.setText(eventclass.getTitle());
        holder.des.setText(eventclass.getDescription());
        holder.date.setText(eventclass.getDate());
        holder.time.setText(eventclass.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView title,des,date,time;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            des=itemView.findViewById(R.id.description);
            time=itemView.findViewById(R.id.month);
            date=itemView.findViewById(R.id.date);
        }
    }
}
