package com.example.gotourapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import com.example.gotourapplication.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//set binding method
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//menu button animation and set onClick for direct to setting activity
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake_animation);
        binding.MenuButton.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this,SettingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            binding.MenuButton.startAnimation(shake);
        });
//
//profile button set onClick
        binding.ProfileButtonLayout.setOnClickListener(null);
//
//if edit text in focus mode, when press anywhere ,edit text exit focus mode and hide soft keyboard
        binding.ActivityMain.setOnClickListener(view -> {
            view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
//
//set bottom navigation
        //set bottom navigation clickable
        binding.bottomNavigationView.setOnClickListener(null);
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        int main_status = 1;
        int profile_status = 0;
        int bottom_navigation_status = 1;
        Intent get_intent = this.getIntent();
        if (get_intent != null){
            main_status = get_intent.getIntExtra("MainStatus",1);
            profile_status = get_intent.getIntExtra("ProfileStatus",8);
            bottom_navigation_status = get_intent.getIntExtra("BottomNavigationStatus",1);
        }
        binding.MainLayout.setVisibility(main_status);
        binding.ProfileLayout.setVisibility(profile_status);
        if (bottom_navigation_status == 1){
            binding.bottomNavigationView.setSelectedItemId(R.id.option);
        }else if (bottom_navigation_status == 2){
            binding.bottomNavigationView.setSelectedItemId(R.id.alarm);
        }else if (bottom_navigation_status == 3){
            binding.bottomNavigationView.setSelectedItemId(R.id.message);
        }else if (bottom_navigation_status == 4){
            binding.bottomNavigationView.setSelectedItemId(R.id.profile);
        }
//
    }
//bottom navigation conditions ((if click on any icon Do something...))
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.option:
                //Todo what will happen when click on option icon
                binding.MainLayout.setVisibility(View.VISIBLE);
                binding.ProfileLayout.setVisibility(View.GONE);
                return true;

            case R.id.alarm:
                //Todo what will happen when click on alarm icon
                return true;

            case R.id.message:
                //Todo what will happen when click on message icon
                return true;

            case R.id.profile:
                //Todo what will happen when click on profile icon
                binding.MainLayout.setVisibility(View.GONE);
                binding.ProfileLayout.setVisibility(View.VISIBLE);
//                Intent intent2 = new Intent(MainActivity.this,ProfileActivity.class);
//                startActivity(intent2);
//                overridePendingTransition(0, 0);


                return true;

        }
        return false;
    }
//
}