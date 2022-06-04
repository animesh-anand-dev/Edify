package com.edify.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class IntroductoryActivity extends AppCompatActivity {

    ImageView imageView;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        imageView= (ImageView) findViewById(R.id.app_name);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottie);

        new Timer().schedule(new TimerTask(){
            public void run() {
                startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
            }
        }, 3000);
    }
}