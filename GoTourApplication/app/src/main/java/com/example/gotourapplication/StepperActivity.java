package com.example.gotourapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.gotourapplication.databinding.ActivityStepperBinding;

public class StepperActivity extends AppCompatActivity {
//set the variable of the stepper dots
    private static final int MAX_STEP = 3;
    private int current_step = 1;
//
    private ActivityStepperBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//set binding method
        binding = ActivityStepperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//create the button to direct user from stepper activity to login activity called login button
        binding.logInButton.setOnClickListener(View -> {
            Intent intent = new Intent(StepperActivity.this,LoginActivity.class);
            startActivity(intent);
//
//the animation of the direct user to login activity
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//
        });

//call function of the stepper ((how to work the stepper))
        initComponent();
//
    }

    private void initComponent() {
//set the first title and sub title that the user should looks at in the first layout of this activity
        binding.stepperTitle.setText(R.string.explore);
        binding.stepperSubTitle.setText(getString(R.string.explore_sub_title));
//
//call the back button function and set it to arrow back button that is invisible in the first layout and visible in the other
        binding.backButton.setOnClickListener(view -> {
            backStep(current_step);
            bottomProgressDots(current_step);
        });
//
//call the next button function and set it to arrow forward button that is visible in the first layout and second layout
        binding.nextButton.setOnClickListener(view -> {
            nextStep(current_step);
            bottomProgressDots(current_step);
        });
//
//call the progress dots function that is visible in the first layout and second layout
        bottomProgressDots(current_step);
//

    }

    private void nextStep(int progress) {
        if (progress < MAX_STEP) {
            progress++;
            current_step = progress;
        }

//set second layout params
        if (progress == 2){
            binding.stepperImage.setImageResource(R.drawable.step_2);

            binding.backButton.setVisibility(View.VISIBLE);

            binding.stepperTitle.setText(R.string.easy_peasy);
            binding.stepperSubTitle.setText(R.string.easy_sub_title);


        }
//
//set third layout params
        else if (progress == 3){
            binding.stepperImage.setImageResource(R.drawable.step_3);

            binding.nextButton.setVisibility(View.GONE);
            binding.logInButton.setVisibility(View.VISIBLE);
            binding.layoutDots.setVisibility(View.GONE);

            binding.stepperTitle.setText(R.string.enjoy_tour);
            binding.stepperSubTitle.setText(R.string.enjoy_sub_title);
        }
//
    }

    private void backStep(int progress) {
        if (progress > 1) {
            progress--;
            current_step = progress;
        }
//set first layout params
        if (progress == 1) {
            binding.stepperImage.setImageResource(R.drawable.step_1);

            binding.backButton.setVisibility(View.INVISIBLE);

            binding.stepperTitle.setText(R.string.explore);
            binding.stepperSubTitle.setText(getString(R.string.explore_sub_title));


        }
//
//set second layout params
        else if (progress == 2) {
            binding.stepperImage.setImageResource(R.drawable.step_2);

            binding.nextButton.setVisibility(View.VISIBLE);
            binding.logInButton.setVisibility(View.GONE);
            binding.layoutDots.setVisibility(View.VISIBLE);

            binding.stepperTitle.setText(R.string.easy_peasy);
            binding.stepperSubTitle.setText(R.string.easy_sub_title);
        }
//
    }

//progress dots ((how to work, margin, size,))
    private void bottomProgressDots(int current_index) {
        current_index--;

        ImageView[] dots = new ImageView[MAX_STEP];

        binding.layoutDots.removeAllViews();
//progress dots loop
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
//
//progress dots size
            int width_height = 22;
//
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
//set dots margin
            params.setMargins(15, 15, 15, 15);
//
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.dot_foreground);
            dots[i].setColorFilter(getResources().getColor(R.color.gray_221), PorterDuff.Mode.SRC_IN);
            binding.layoutDots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.dot_foreground);
            dots[current_index].setColorFilter(getResources().getColor(R.color.base_orange), PorterDuff.Mode.SRC_IN);
        }
    }

}