package com.example.gotourapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
        binding.ProfileButton.setOnClickListener(null);
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
        //binding.bottomNavigationView.setOnNavigationItemSelectedListener(this); //????
        //set option icon as selected icon
        binding.bottomNavigationView.setSelectedItemId(R.id.option);
//
    }
//bottom navigation conditions ((if click on any icon Do something...))
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.option:
                //Todo what will happen when click on option icon
                return true;

            case R.id.alarm:
                //Todo what will happen when click on alarm icon
                return true;

            case R.id.message:
                //Todo what will happen when click on message icon
                return true;

            case R.id.profile:
                //Todo what will happen when click on profile icon
                return true;

        }
        return false;
    }
//
}