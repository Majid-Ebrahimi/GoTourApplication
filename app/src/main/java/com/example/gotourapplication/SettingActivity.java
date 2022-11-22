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
        Intent intent = new Intent(SettingActivity.this,MainActivity.class);
        binding.GoToProfileLayout.setOnClickListener(View ->{
            intent.putExtra("MainStatus",8);
            intent.putExtra("ProfileStatus",1);
            intent.putExtra("BottomNavigationStatus",4);
            startActivity(intent);
        });
//
//Back To Profile Button Todo: write a better comment
        binding.GoToProfileButton.setOnClickListener(View -> {
            intent.putExtra("MainStatus",8);
            intent.putExtra("ProfileStatus",1);
            intent.putExtra("BottomNavigationStatus",4);
            startActivity(intent);
            startActivity(intent);
        });
//
//Notification Button Todo: write a better comment
        binding.Notification.setOnClickListener(null);
//
//Language Button Todo: write a better comment
        binding.Language.setOnClickListener(null);
//
//Currency Button Todo: write a better comment
        binding.Currency.setOnClickListener(null);
//
//PaymentMethod Button Todo: write a better comment
        binding.PaymentMethod.setOnClickListener(null);
//
//PrivacyPolicy Button Todo: write a better comment
        binding.PrivacyPolicy.setOnClickListener(null);
//
//TermsOfUse Button Todo: write a better comment
        binding.TermsOfUse.setOnClickListener(null);
//

    }


}