package com.example.stashtask2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    //Declare imgSplashLogo as private variable of type Imageview
    private ImageView imgSplashLogo;
    //Declare txtSplash as private variable of type TextView
    private TextView txtSplash;

    //Declare animImage and animText as variables of type Animation
    Animation animImage, animText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Set imgSplashLogo to its corresponding ImageView in activity_splash.xml
        imgSplashLogo = findViewById(R.id.imgSplashLogo);
        //Set txtSplash to its corresponding TextView in activity_splash.xml
        txtSplash = findViewById(R.id.txtStash);

        //Set animImage to load its animation from the img_animation.xml code in the anim folder
        animImage = AnimationUtils.loadAnimation(this, R.anim.img_animation);
        //Set animText to load its animation from the txt_animation.xml code in the anim folder
        animText = AnimationUtils.loadAnimation(this, R.anim.txt_animation);

        //Set imgSplashLogo's animation to animImage
        imgSplashLogo.setAnimation(animImage);
        //Set txtSplash's animation to animText
        txtSplash.setAnimation(animText);

        //Create a countdown timer for the animation
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                //Start the main screen after the splash screen has completed the animation
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                //Finish this activity
                finish();
            }
        }.start();
    }
}