package com.example.fdroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name,price;
   FoodAdapter(Context context,ArrayList name,ArrayList price){
        this.context = context;
        this.name = name;
        this.price = price;
    }
    @NonNull
    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, int position) {
        holder.foodName.setText(String.valueOf(name.get(position)));
        holder.foodPrice.setText("₹ "+String.valueOf(price.get(position)));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView foodName,foodPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
        }
    }
}
