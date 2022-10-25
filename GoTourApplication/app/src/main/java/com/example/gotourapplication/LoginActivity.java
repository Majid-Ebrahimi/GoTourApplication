package com.example.gotourapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.gotourapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//set binding method
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//if edit text in focus mode, when press anywhere ,edit text exit focus mode and hide soft keyboard
        binding.activityLogin.setOnClickListener(view -> {
            view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
//
//Google button
binding.loginWithGoogle.setOnClickListener(view ->{
});
//
        binding.signUpHere.setOnClickListener(view ->{
            Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(intent);

            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
}