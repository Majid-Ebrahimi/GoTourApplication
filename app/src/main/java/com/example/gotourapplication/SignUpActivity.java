package com.example.gotourapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.example.gotourapplication.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    private int cur = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//set binding method
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//if edit text in focus mode, when press anywhere ,edit text exit focus mode and hide soft keyboard
        binding.ActivitySignUp.setOnClickListener(view -> {
            view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
//
//clickable "terms of use" text in textView
        SpannableString ss = new SpannableString(getString(R.string.terms_of_use_text));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(SignUpActivity.this, "Terms Of Use Pressed...", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void updateDrawState(TextPaint ds){
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                binding.TermsOfUseSpannedText.setHighlightColor(getColor(R.color.white));

            }
        };
        ss.setSpan(clickableSpan,23,36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.TermsOfUseSpannedText.setText(ss);
        binding.TermsOfUseSpannedText.setMovementMethod(LinkMovementMethod.getInstance());
//
//if terms of use accepted, the sign up button will enable
        binding.TermsOfUseCheckBox.setOnClickListener(view -> checkBox(cur));
//
    }


//function of checkBox checked or not, if checkBox checked the signUp button will enable
    private void checkBox(int progress) {
        int max = 2;
        if (progress < max) {
            binding.TermsOfUseCheckBox.setChecked(true);
            binding.SignUpButton.setEnabled(true);
            progress++;
            cur = progress;
        }
        else if (progress== max){
            binding.TermsOfUseCheckBox.setChecked(false);
            binding.SignUpButton.setEnabled(false);
            progress--;
            cur = progress;
        }

    }
//
}