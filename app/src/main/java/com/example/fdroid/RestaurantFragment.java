package com.example.fdroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestaurantFragment extends Fragment {
    RecyclerView RestaurantFragment_recyclerView;
    ArrayList<String> RestaurantFragment_restaurantName,RestaurantFragment_category,RestaurantFragment_area;
    CustomAdapter RestaurantFragment_customAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant,container,false);
        RestaurantFragment_recyclerView = view.findViewById(R.id.restaurantBox);
        RestaurantFragment_restaurantName = new ArrayList<>();
        RestaurantFragment_category = new ArrayList<>();
        RestaurantFragment_area = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Restaurants");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    RestaurantData RestaurantFragment_restaurantInfo = dataSnapshot.getValue(RestaurantData.class);
                    RestaurantFragment_restaurantName.add(RestaurantFragment_restaurantInfo.getName());
                    RestaurantFragment_category.add(RestaurantFragment_restaurantInfo.getCategory());
                    RestaurantFragment_area.add(RestaurantFragment_restaurantInfo.getArea());
                }
                System.out.println(RestaurantFragment_restaurantName);
                System.out.println(RestaurantFragment_area);
                System.out.println(RestaurantFragment_category);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        RestaurantFragment_customAdapter = new CustomAdapter(getContext(),RestaurantFragment_restaurantName,RestaurantFragment_area,RestaurantFragment_category);
        RestaurantFragment_recyclerView.setAdapter(RestaurantFragment_customAdapter);
        RestaurantFragment_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}
