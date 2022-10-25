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
        binding.LoginNowButton.setOnClickListener(View -> {
            Intent intent = new Intent(StepperActivity.this,LoginActivity.class);
            startActivity(intent);
//
//the animation of the direct user to login activity
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
//
        });

//call function of the stepper ((how to work the stepper))
        initComponent();
//
    }

    private void initComponent() {
//set the first title and sub title that the user should looks at in the first layout of this activity
        binding.StepperTitle.setText(R.string.explore);
        binding.StepperSubTitle.setText(getString(R.string.explore_sub_title));
//
//call the back button function and set it to arrow back button that is invisible in the first layout and visible in the other
        binding.BackButton.setOnClickListener(view -> {
            backStep(current_step);
            bottomProgressDots(current_step);
        });
//
//call the next button function and set it to arrow forward button that is visible in the first layout and second layout
        binding.NextButton.setOnClickListener(view -> {
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
            binding.StepperImage.setImageResource(R.drawable.step_2);

            binding.BackButton.setVisibility(View.VISIBLE);

            binding.StepperTitle.setText(R.string.easy_peasy);
            binding.StepperSubTitle.setText(R.string.easy_sub_title);


        }
//
//set third layout params
        else if (progress == 3){
            binding.StepperImage.setImageResource(R.drawable.step_3);

            binding.NextButton.setVisibility(View.GONE);
            binding.LoginNowButton.setVisibility(View.VISIBLE);
            binding.StepperDots.setVisibility(View.GONE);

            binding.StepperTitle.setText(R.string.enjoy_tour);
            binding.StepperSubTitle.setText(R.string.enjoy_sub_title);
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
            binding.StepperImage.setImageResource(R.drawable.step_1);

            binding.BackButton.setVisibility(View.INVISIBLE);

            binding.StepperTitle.setText(R.string.explore);
            binding.StepperSubTitle.setText(getString(R.string.explore_sub_title));


        }
//
//set second layout params
        else if (progress == 2) {
            binding.StepperImage.setImageResource(R.drawable.step_2);

            binding.NextButton.setVisibility(View.VISIBLE);
            binding.LoginNowButton.setVisibility(View.GONE);
            binding.StepperDots.setVisibility(View.VISIBLE);

            binding.StepperTitle.setText(R.string.easy_peasy);
            binding.StepperSubTitle.setText(R.string.easy_sub_title);
        }
//
    }

//progress dots ((how to work, margin, size,))
    private void bottomProgressDots(int current_index) {
        current_index--;

        ImageView[] dots = new ImageView[MAX_STEP];

        binding.StepperDots.removeAllViews();
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
            dots[i].setColorFilter(getColor(R.color.light_grey), PorterDuff.Mode.SRC_IN);
            binding.StepperDots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.dot_foreground);
            dots[current_index].setColorFilter(getColor(R.color.base_orange), PorterDuff.Mode.SRC_IN);
        }
    }

}