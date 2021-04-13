package com.example.fdroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    TextView firstName, lastName, emailId;
    Button logoutButton;
    TextView updateDetails;
    RecyclerView pastOrders;
    GoogleSignInClient mGoogleSignInClient;
    ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firstName = findViewById(R.id.profileActivity_firstNameText);
        lastName = findViewById(R.id.profileActivity_lastNameText);
        emailId = findViewById(R.id.profileActivity_emailText);
        logoutButton = findViewById(R.id.profileActivity_logoutButton);
        pastOrders = findViewById(R.id.profileActivity_pastOrdersRecyclerView);
        updateDetails = findViewById(R.id.profileActivity_updateInfoText);
        profilePicture = findViewById(R.id.profileActivity_profilePicture);

        SharedPreferences sp = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        firstName.setText(sp.getString("userNameKey",""));
        lastName.setText(sp.getString("userLastNameKey",""));
        emailId.setText(sp.getString("userEmailKey",""));
        String profilePictureUri = sp.getString("userPhotoKey","");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut();
                FirebaseAuth.getInstance().signOut();
                SharedPreferences.Editor editor = sp.edit();
                sp.edit().clear().commit();
                Toast.makeText(ProfileActivity.this, "Logged out!", Toast.LENGTH_SHORT).show();
                finishAffinity();
                Intent i = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(i);
            }
        });

        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://myaccount.google.com/personal-info?rapt=AEjHL4O6_rCZQYO8GeqT2_AZ0U98g4FrKTimgp9xHIH9OuiPeJMAymUqihxIP4Wz8bKyf93wMjb2wRonXXtwM0HsESARSkwF7w";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });

    }
}