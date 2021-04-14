package com.example.fdroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> restoName,orderId,totalPrice,date;

    public HistoryAdapter(Context context, ArrayList restoName, ArrayList orderId, ArrayList totalPrice, ArrayList date) {
        this.context = context;
        this.restoName = restoName;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_history_row, parent,false);
        return new HistoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyViewHolder holder, int position) {
        holder.restName_tv.setText(String.valueOf(restoName.get(position)));
        holder.date_tv.setText(String.valueOf(date.get(position)));
        holder.orderId_tv.setText(String.valueOf(orderId.get(position)));
        holder.totalPrice_tv.setText("₹ "+String.valueOf(totalPrice.get(position)));
    }

    @Override
    public int getItemCount() {
        return orderId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView restName_tv,orderId_tv,totalPrice_tv,date_tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            restName_tv = itemView.findViewById(R.id.restOrdered);
            orderId_tv = itemView.findViewById(R.id.orderId);
            totalPrice_tv = itemView.findViewById(R.id.orderTotalPrice);
            date_tv = itemView.findViewById(R.id.orderDate);
        }
    }
}
