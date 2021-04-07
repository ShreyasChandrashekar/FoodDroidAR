package com.example.fdroid;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name,area,category;
    CustomAdapter(Context context,ArrayList name,ArrayList area,ArrayList category){
        this.context = context;
        this.name = name;
        this.area = area;
        this.category = category;
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
    }

    @Override
    public int getItemCount() {
        return name.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName,restaurantCategory,restaurantArea;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantCategory = itemView.findViewById(R.id.category);
            restaurantArea = itemView.findViewById(R.id.area);
        }
    }
}
