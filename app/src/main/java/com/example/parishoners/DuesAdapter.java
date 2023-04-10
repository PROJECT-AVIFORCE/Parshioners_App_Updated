package com.example.parishoners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DuesAdapter extends RecyclerView.Adapter<DuesAdapter.MyViewHolder> {
    Context context;
    ArrayList<Duesdataclass> list;

    public DuesAdapter(Context context, ArrayList<Duesdataclass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DuesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(context).inflate(R.layout.payment_task,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuesAdapter.MyViewHolder holder, int position) {
        Duesdataclass duesdataclass=list.get(position);
        holder.dtitle.setText(duesdataclass.getTitle());
        holder.ddes.setText(duesdataclass.getDescription());
        holder.dtime.setText("due time :");
        holder.duetime.setText(duesdataclass.getTime());
        holder.ddate.setText(duesdataclass.getDate());
        holder.due.setText("due date :");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView dtitle,ddes,ddate,dtime,due,duetime;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dtitle=itemView.findViewById(R.id.dtitle);
            ddes=itemView.findViewById(R.id.ddescription);
            ddate=itemView.findViewById(R.id.ddate);
            dtime=itemView.findViewById(R.id.duestime);
            due=itemView.findViewById(R.id.duesdate);
            duetime=itemView.findViewById(R.id.newtime);
        }
    }
}
