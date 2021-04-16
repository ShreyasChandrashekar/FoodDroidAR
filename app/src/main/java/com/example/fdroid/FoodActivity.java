package com.example.fdroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodActivity extends AppCompatActivity {
    RecyclerView FoodActivity_recyclerView;
    ArrayList<String> FoodActivity_foodName,FoodActivity_foodPrice,FoodActivity_AR,FoodActivity_foodImage;
    FoodAdapter FoodActivity_customAdapter;
    HashMap<String,ArrayList> FoodActivity_orderDetails;
    TextView FoodActivity_restName,FoodActivity_totalPrice;
    ImageButton FoodActivity_back;
    RelativeLayout FoodActivity_checkOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        FoodActivity_recyclerView = findViewById(R.id.foodBox);
        FoodActivity_foodName = new ArrayList<>();
        FoodActivity_foodPrice = new ArrayList<>();
        FoodActivity_AR = new ArrayList<>();
        FoodActivity_foodImage = new ArrayList<>();
        FoodActivity_orderDetails = new HashMap<>();
        FoodActivity_restName = findViewById(R.id.restName);
        FoodActivity_totalPrice = findViewById(R.id.totalPrice);
        FoodActivity_back = findViewById(R.id.backButton);
        FoodActivity_checkOut = findViewById(R.id.checkOut);
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
                    FoodActivity_AR.add(FoodActivity_foodInfo.getAR());
                    FoodActivity_foodImage.add(FoodActivity_foodInfo.getImage());
                }
                System.out.println(FoodActivity_foodName);
                System.out.println(FoodActivity_foodPrice);
                FoodActivity_customAdapter = new FoodAdapter(FoodActivity.this,FoodActivity_totalPrice,FoodActivity_foodName,FoodActivity_foodPrice,FoodActivity_AR,FoodActivity_foodImage,FoodActivity_orderDetails);
                FoodActivity_recyclerView.setAdapter(FoodActivity_customAdapter);
                FoodActivity_recyclerView.setLayoutManager(new LinearLayoutManager(FoodActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FoodActivity_checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FoodActivity_orderDetails.size()>0){
                    Intent intent = new Intent(FoodActivity.this,OrderDetails.class);
                    System.out.println(FoodActivity_totalPrice.getText().toString());
                    intent.putExtra("restName",FoodActivity_restName.getText().toString());
                    intent.putExtra("totalPrice",FoodActivity_totalPrice.getText().toString());
                    intent.putExtra("details",FoodActivity_orderDetails);
                    startActivity(intent);
                }
            }
        });
        FoodActivity_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}