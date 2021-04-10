package com.example.fdroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name,price,ar,image;
    private TextView totalPrice;
   FoodAdapter(Context context,TextView totalPrice,ArrayList name,ArrayList price,ArrayList ar,ArrayList image){
        this.context = context;
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
        this.ar = ar;
        this.image = image;
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
        Picasso.get().load(String.valueOf(image.get(position))).into(holder.foodImage);
        holder.arPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                Uri intentUri =
                        Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
                                .appendQueryParameter("file", String.valueOf(ar.get(position)))
                                .appendQueryParameter("mode", "3d_preferred")
                                .appendQueryParameter("title", String.valueOf(name.get(position)))
                                .build();
                sceneViewerIntent.setData(intentUri);
                sceneViewerIntent.setPackage("com.google.ar.core");
                context.startActivity(sceneViewerIntent);
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quant = Integer.parseInt(holder.foodQuantity.getText().toString());
                int indiPrice = Integer.parseInt(String.valueOf(price.get(position)));
                int price = Integer.parseInt(totalPrice.getText().toString());
                price += indiPrice;
                quant+=1;
                System.out.println(quant);
                holder.foodQuantity.setText(String.valueOf(quant));
                totalPrice.setText(String.valueOf(price));

            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quant = Integer.parseInt(holder.foodQuantity.getText().toString());
                if(quant != 0){
                    quant-=1;
                    int indiPrice = Integer.parseInt(String.valueOf(price.get(position)));
                    int price = Integer.parseInt(totalPrice.getText().toString());
                    holder.foodQuantity.setText(String.valueOf(quant));
                    price -= indiPrice;
                    totalPrice.setText(String.valueOf(price));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView foodName,foodPrice,foodQuantity;
        ImageButton plus,minus;
        ImageView arPop,foodImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodQuantity = itemView.findViewById(R.id.foodQuantity);
            foodImage = itemView.findViewById(R.id.foodImage);
            plus = itemView.findViewById(R.id.foodIncrease);
            minus = itemView.findViewById(R.id.foodReduce);
            arPop = itemView.findViewById(R.id.foodImage);
        }
    }
}
