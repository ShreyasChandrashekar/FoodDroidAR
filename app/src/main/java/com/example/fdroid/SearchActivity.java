package com.example.fdroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchActivity extends AppCompatActivity {

    EditText searchField;
    RecyclerView RestaurantFragment_recyclerView;
    private DatabaseReference restaurantDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        restaurantDatabase = FirebaseDatabase.getInstance().getReference("Restaurants");

        searchField = findViewById(R.id.searchField);
        RestaurantFragment_recyclerView = findViewById(R.id.restaurantBox);
        RestaurantFragment_recyclerView.setHasFixedSize(true);
        RestaurantFragment_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = searchField.getText().toString();
                firebaseRestaurantSearch(SearchActivity.this, searchText);
            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment MainActivity_selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.home:
                            Intent homeIntent = new Intent(SearchActivity.this,MainActivity.class);
                            startActivity(homeIntent);
                            break;
                        case R.id.search:
                            Intent searchIntent = new Intent(SearchActivity.this,SearchActivity.class);
                            startActivity(searchIntent);
                            break;
                        case R.id.profile:
                            Intent profileIntent = new Intent(SearchActivity.this,ProfileActivity.class);
                            startActivity(profileIntent);
                            break;
                    }

                    return true;
                }
            };

    private void firebaseRestaurantSearch(Context context, String searchText) {

        Query firebaseSearchQuery = restaurantDatabase.orderByChild("Name").startAt(searchText).endAt(searchText +"\uf8ff");

        FirebaseRecyclerAdapter<RestaurantData, RestaurantViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RestaurantData, RestaurantViewHolder>(
                RestaurantData.class,
                R.layout.restaurant_row,
                RestaurantViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            protected void populateViewHolder(RestaurantViewHolder restaurantViewHolder, RestaurantData restaurantData, int i) {
                restaurantViewHolder.setDetails(getApplicationContext(), context, restaurantData.getId(),restaurantData.getName(),restaurantData.getCategory(),restaurantData.getImage());
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

        public void setDetails(Context ctx,Context context,String restaurant_Id,String restaurant_Name, String restaurantCategory, String restaurant_Image){

            LinearLayout restaurantRow = view.findViewById(R.id.restaurantRowLayout);
            restaurantRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent foodIntent = new Intent(context, FoodActivity.class);
                    foodIntent.putExtra("restaurantId",String.valueOf(restaurant_Id));
                    foodIntent.putExtra("restaurantName",String.valueOf(restaurant_Name));
                    context.startActivity(foodIntent);
                }
            });
            TextView restaurantName = view.findViewById(R.id.restaurantName);
            TextView category = view.findViewById(R.id.category);
            ImageView restaurantImage = view.findViewById(R.id.restaurantImage);

            restaurantName.setText(restaurant_Name);
            category.setText(restaurantCategory);
            Glide.with(ctx).load(restaurant_Image).into(restaurantImage);

        }
    }

}
