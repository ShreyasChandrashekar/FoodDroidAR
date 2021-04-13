package com.example.fdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    VideoView splashScreenVideo;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        splashScreenVideo = findViewById(R.id.splashScreen_video);

        mAuth = FirebaseAuth.getInstance();

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splashscreen);

        splashScreenVideo.setVideoURI(video);

        splashScreenVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                Intent i;
                if(currentUser != null){
                    i = new Intent(getApplicationContext(), MainActivity.class);
                }
                else{
                    i = new Intent(getApplicationContext(), LoginPage.class);
                }
                startActivity(i);
                finish();
            }
        });

        splashScreenVideo.start();
    }
}