package com.example.gotourapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.gotourapplication.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {

    private ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//back button animation and set onClick for direct to main activity
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake_animation);
        binding.MainBackButton.setOnClickListener(view ->{
            Intent intent = new Intent(SettingActivity.this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            binding.MainBackButton.startAnimation(shake);
        });
//
//Back To Profile Button Todo: write a better comment
        binding.GoToProfileLayout.setOnClickListener(null);

//
//Back To Profile Button Todo: write a better comment
        binding.GoToProfileButton.setOnClickListener(null);

//
    }


}