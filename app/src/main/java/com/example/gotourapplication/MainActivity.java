package com.example.gotourapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gotourapplication.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
//TODO Recycler VIEW
    ArrayList<Integer> imagesSource;
    ArrayList<String> titlesSource;
    ArrayList<String> pricesSource;
    ArrayList<String> locationsSource;

    LinearLayoutManager linearLayoutManager;
    sliderImageAdapter sliderImageAdapter;

    public class sliderImageAdapter extends RecyclerView.Adapter<sliderImageAdapter.MyHolder> {
        ArrayList<Integer> imagesArrayList;
        ArrayList<String> titlesArrayList;
        ArrayList<String> pricesArrayList;
        ArrayList<String> locationsArrayList;

        public sliderImageAdapter(ArrayList<Integer> imagesArrayList, ArrayList<String> titlesArrayList, ArrayList<String> pricesArrayList, ArrayList<String> locationsArrayList) {
            this.imagesArrayList = imagesArrayList;
            this.titlesArrayList = titlesArrayList;
            this.pricesArrayList = pricesArrayList;
            this.locationsArrayList = locationsArrayList;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_view_card_items, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.ImagesCardView.setImageResource(imagesArrayList.get(position));
            holder.TitlesTextView.setText(titlesArrayList.get(position));
            holder.PricesTextView.setText(pricesArrayList.get(position));
            holder.LocationsTextView.setText(locationsArrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return imagesArrayList.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            ImageView ImagesCardView;
            TextView TitlesTextView;
            TextView PricesTextView;
            TextView LocationsTextView;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                ImagesCardView = itemView.findViewById(R.id.ImagesCardView);
                TitlesTextView = itemView.findViewById(R.id.TitlesTextView);
                PricesTextView = itemView.findViewById(R.id.PricesTextView);
                LocationsTextView = itemView.findViewById(R.id.LocationsTextView);
            }
        }

    }


    /////////////-------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//set binding method
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
////////////////TODO RecyclerView    --  -- ---- -- -- -- --- --- --- -- --- -- ---- --- ---
        //Setting the data source

//        dataSource = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.images)));

        imagesSource = new ArrayList<>();
        imagesSource.add(R.drawable.image_1);
        imagesSource.add(R.drawable.image_2);
        imagesSource.add(R.drawable.image_3);
        imagesSource.add(R.drawable.image_4);
        imagesSource.add(R.drawable.image_5);
        imagesSource.add(R.drawable.image_6);
        imagesSource.add(R.drawable.image_7);
        imagesSource.add(R.drawable.image_8);
        imagesSource.add(R.drawable.image_9);
        imagesSource.add(R.drawable.image_10);

        titlesSource = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.images_title)));

        pricesSource = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.images_prices)));

        locationsSource = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.images_location)));

        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        sliderImageAdapter = new sliderImageAdapter(imagesSource, titlesSource, pricesSource, locationsSource);
        binding.SliderImage.setLayoutManager(linearLayoutManager);
        binding.SliderImage.setAdapter(sliderImageAdapter);


        binding.SliderImage.setClickable(true);

        //////////////

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
    @SuppressLint("NonConstantResourceId")
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
                return true;

        }
        return false;
    }
//
}

