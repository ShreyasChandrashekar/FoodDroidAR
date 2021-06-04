package com.example.fdroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchActivity extends AppCompatActivity {

    EditText searchField;
    Button searchButton;
    RecyclerView RestaurantFragment_recyclerView;
    private DatabaseReference restaurantDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        restaurantDatabase = FirebaseDatabase.getInstance().getReference("Restaurants");

        searchField = (EditText) findViewById(R.id.searchField);
        searchButton = (Button) findViewById(R.id.searchButton);
        RestaurantFragment_recyclerView = (RecyclerView)findViewById(R.id.restaurantBox);
        RestaurantFragment_recyclerView.setHasFixedSize(true);
        RestaurantFragment_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchField.getText().toString();
               firebaseRestaurantSearch(searchText);
            }
        });
    }

    public void onClick(View v) {
        searchField.getText().clear(); //or you can use editText.setText("");
    }

    private void firebaseRestaurantSearch(String searchText) {

        Query firebaseSearchQuery = restaurantDatabase.orderByChild("Name").startAt(searchText).endAt(searchText +"\uf8ff");

        FirebaseRecyclerAdapter<RestaurantData,RestaurantViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RestaurantData, RestaurantViewHolder>(
                RestaurantData.class,
                R.layout.restaurant_row,
                RestaurantViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            protected void populateViewHolder(RestaurantViewHolder restaurantViewHolder, RestaurantData restaurantData, int i) {

                restaurantViewHolder.setDetails(getApplicationContext(),restaurantData.getName(),restaurantData.getCategory(),restaurantData.getImage());

            }
        };
        RestaurantFragment_recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class RestaurantViewHolder extends RecyclerView.ViewHolder{

        View view;
        public RestaurantViewHolder(View itemView){
            super(itemView);
            view = itemView;
        }

        public void setDetails(Context ctx,String restaurant_Name, String restaurantCategory, String restaurant_Image){

            TextView restaurantName = (TextView)view.findViewById(R.id.restaurantName);
            TextView category = (TextView)view.findViewById(R.id.category);
            ImageView restaurantImage = (ImageView)view.findViewById(R.id.restaurantImage);

            restaurantName.setText(restaurant_Name);
            category.setText(restaurantCategory);
            Glide.with(ctx).load(restaurant_Image).into(restaurantImage);

        }
    }

}
