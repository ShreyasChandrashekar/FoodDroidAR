package com.example.fdroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter<context> extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    private Context context;
    private ArrayList foodName;
    private ArrayList foodQuant;

    public OrderAdapter(Context context, ArrayList foodName, ArrayList foodQuant, ArrayList foodPrice) {
        this.context = context;
        this.foodName = foodName;
        this.foodQuant = foodQuant;
        this.foodPrice = foodPrice;
    }

    private ArrayList foodPrice;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_order_row, parent,false);
        return new OrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        holder.name.setText(String.valueOf(foodName.get(position)));
        holder.quant.setText(String.valueOf(foodQuant.get(position)));
        holder.price.setText(String.valueOf("₹"+foodPrice.get(position)));
    }

    @Override
    public int getItemCount() {
        return foodName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,quant,price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodDetailName);
            quant = itemView.findViewById(R.id.foodDetailQuant);
            price = itemView.findViewById(R.id.foodDetailPrice);
        }
    }
}
