package com.example.parishoners;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

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
    FirebaseAuth    auth = FirebaseAuth.getInstance();
       String  UserID = Objects.requireNonNull(auth.getCurrentUser()).getUid();

        if( UserID.equals("kaAwxZeCUDQpB421qKc5ArGfXci2")) {
            holder.paynow.setVisibility(View.INVISIBLE);

        }
        else
        {
            holder.paynow.setVisibility(View.VISIBLE);
            holder.paynow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), payment.class);
                    v.getContext().startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView dtitle,ddes,ddate,dtime,due,duetime,paynow;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dtitle=itemView.findViewById(R.id.dtitle);
            ddes=itemView.findViewById(R.id.ddescription);
            ddate=itemView.findViewById(R.id.ddate);
            dtime=itemView.findViewById(R.id.duestime);
            due=itemView.findViewById(R.id.duesdate);
            duetime=itemView.findViewById(R.id.newtime);
            paynow = itemView.findViewById(R.id.status);

        }
    }
}
