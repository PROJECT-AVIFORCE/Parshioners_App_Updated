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

import java.util.ArrayList;
import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {


    public Context context;
    List<FeaturedHelperClass> features;
    SelectListener listener;

    public FeaturedAdapter(Context context,List<FeaturedHelperClass> features,SelectListener listener) {
        this.context=context;
        this.features = features;
        this.listener=listener;
    }


    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = features.get(position);



        holder.image.setImageResource(features.get(position).getImage());
        holder.title.setText(features.get(position).getTitle());
        holder.desc.setText(features.get(position).getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onitemclick(featuredHelperClass);

            }
        });



    }

    @Override
    public int getItemCount() {
        return features.size();
    }


    public class FeaturedViewHolder extends RecyclerView.ViewHolder {


        public ImageView image;
        public TextView title, desc;
        public CardView cardView;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //hooks
            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            desc = itemView.findViewById(R.id.featured_description);
            cardView=itemView.findViewById(R.id.featured_cardview);





        }
    }
}
