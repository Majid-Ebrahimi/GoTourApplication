package com.example.gotourapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gotourapplication.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    private static int SPLASH_SCREEN_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
//set binding method
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//set splash window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//create the animation to fade the splash activity
        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(500);
        fadeOut.setDuration(1800);
        binding.splashImage.setAnimation(fadeOut);
        binding.goTourLogo.setAnimation(fadeOut);
        binding.textLogo.setAnimation(fadeOut);

//automatic direct the user from splash activity to stepper activity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this,StepperActivity.class);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN_TIMEOUT);
    }

}
