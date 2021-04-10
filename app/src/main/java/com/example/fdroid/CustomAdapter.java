package com.example.fdroid;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name,area,category,id,image;
    CustomAdapter(Context context,ArrayList name,ArrayList area,ArrayList category,ArrayList id,ArrayList image){
        this.context = context;
        this.name = name;
        this.area = area;
        this.category = category;
        this.image = image;
        this.id = id;
    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.restaurant_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.restaurantName.setText(String.valueOf(name.get(position)));
        holder.restaurantCategory.setText(String.valueOf(category.get(position)));
        holder.restaurantArea.setText(String.valueOf(area.get(position)));
        Picasso.get().load(String.valueOf(image.get(position))).into(holder.restaurantImage);
        holder.restaurantRowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodIntent = new Intent(context,FoodActivity.class);
                foodIntent.putExtra("restaurantId",String.valueOf(id.get(position)));
                foodIntent.putExtra("restaurantName",String.valueOf(name.get(position)));
                context.startActivity(foodIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName,restaurantCategory,restaurantArea;
        ImageView restaurantImage;
        LinearLayout restaurantRowLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantCategory = itemView.findViewById(R.id.category);
            restaurantArea = itemView.findViewById(R.id.area);
            restaurantRowLayout = itemView.findViewById(R.id.restaurantRowLayout);
        }
    }
}
