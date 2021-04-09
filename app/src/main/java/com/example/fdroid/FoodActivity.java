package com.example.fdroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
    RecyclerView FoodActivity_recyclerView;
    ArrayList<String> FoodActivity_foodName,FoodActivity_foodPrice;
    FoodAdapter FoodActivity_customAdapter;
    TextView FoodActivity_restName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        FoodActivity_recyclerView = findViewById(R.id.foodBox);
        FoodActivity_foodName = new ArrayList<>();
        FoodActivity_foodPrice = new ArrayList<>();
        FoodActivity_restName = findViewById(R.id.restName);
        Intent FoodActivity_extras = getIntent();
        String FoodActivity_restaurantID = FoodActivity_extras.getStringExtra("restaurantId");
        String FoodActivity_restaurantName = FoodActivity_extras.getStringExtra("restaurantName");
        FoodActivity_restName.setText(FoodActivity_restaurantName);
        System.out.println(FoodActivity_restaurantID);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Restaurants").child(FoodActivity_restaurantID).child("Food");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    FoodData FoodActivity_foodInfo = dataSnapshot.getValue(FoodData.class);
                    FoodActivity_foodName.add(FoodActivity_foodInfo.getName());
                    FoodActivity_foodPrice.add(FoodActivity_foodInfo.getPrice());
                }
                System.out.println(FoodActivity_foodName);
                System.out.println(FoodActivity_foodPrice);
                FoodActivity_customAdapter = new FoodAdapter(FoodActivity.this,FoodActivity_foodName,FoodActivity_foodPrice);
                FoodActivity_recyclerView.setAdapter(FoodActivity_customAdapter);
                FoodActivity_recyclerView.setLayoutManager(new LinearLayoutManager(FoodActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}