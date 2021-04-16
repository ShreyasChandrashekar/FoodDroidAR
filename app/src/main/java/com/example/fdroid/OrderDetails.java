package com.example.fdroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class OrderDetails extends AppCompatActivity {
    RecyclerView OrderDetails_orderSummary;
    TextView OrderDetails_restaurantName,OrderDetails_foodTotal,OrderDetails_finalTotal;
    LinearLayout OrderDetails_payBar;
    HashMap<String,ArrayList> OrderDetails_foodInfo;
    ArrayList<String> OrderDetails_foodName,OrderDetails_foodQuant,OrderDetails_foodPrice;
    OrderAdapter OrderDetails_customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        OrderDetails_restaurantName = findViewById(R.id.orderRestName);
        OrderDetails_foodTotal = findViewById(R.id.foodTotal);
        OrderDetails_orderSummary = findViewById(R.id.foodTab);
        OrderDetails_finalTotal = findViewById(R.id.finalTotal);
        OrderDetails_payBar = findViewById(R.id.payBar);
        OrderDetails_foodName = new ArrayList<>();
        OrderDetails_foodPrice = new ArrayList<>();
        OrderDetails_foodQuant = new ArrayList<>();
        Intent OrderDetails_extras = getIntent();
        OrderDetails_restaurantName.setText(OrderDetails_extras.getStringExtra("restName"));
        OrderDetails_foodTotal.setText("₹"+OrderDetails_extras.getStringExtra("totalPrice"));
        int finalTotal = Integer.parseInt(OrderDetails_extras.getStringExtra("totalPrice"))+10+20;
        OrderDetails_finalTotal.setText("₹"+finalTotal);
        OrderDetails_foodInfo = (HashMap<String, ArrayList>)OrderDetails_extras.getSerializableExtra("details");
        System.out.println(OrderDetails_foodInfo);
        for(String i : OrderDetails_foodInfo.keySet()){
            OrderDetails_foodName.add(i);
        }
        for(ArrayList i : OrderDetails_foodInfo.values()){
            OrderDetails_foodQuant.add(String.valueOf(i.get(0)));
            OrderDetails_foodPrice.add(String.valueOf(i.get(1)));
        }
        OrderDetails_customAdapter = new OrderAdapter(OrderDetails.this,OrderDetails_foodName,OrderDetails_foodQuant,OrderDetails_foodPrice);
        OrderDetails_orderSummary.setAdapter(OrderDetails_customAdapter);
        OrderDetails_orderSummary.setLayoutManager(new LinearLayoutManager(OrderDetails.this));
        OrderDetails_payBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("Orders");
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
                Date date = new Date();
                SharedPreferences sp = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
                String email = sp.getString("userEmailKey","");
                String key = reference.push().getKey();
                OrderData data = new OrderData(OrderDetails_extras.getStringExtra("restName"),key,String.valueOf(finalTotal),dateFormat.format(date),email);
                reference.child(email.replace(".","")).child(key).setValue(data);
                Toast.makeText(getApplicationContext(),"Order Confirmed",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderDetails.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}