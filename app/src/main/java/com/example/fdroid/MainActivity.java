package com.example.fdroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new RestaurantFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment MainActivity_selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.home:
                            MainActivity_selectedFragment = new RestaurantFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    MainActivity_selectedFragment).commit();
                            break;
                        case R.id.search:
                            Intent searchIntent = new Intent(MainActivity.this,SearchActivity.class);
                            startActivity(searchIntent);
                            break;
                        case R.id.profile:
                            Intent profileIntent = new Intent(MainActivity.this,ProfileActivity.class);
                            startActivity(profileIntent);
                            break;
                    }

                    return true;
                }
            };
}