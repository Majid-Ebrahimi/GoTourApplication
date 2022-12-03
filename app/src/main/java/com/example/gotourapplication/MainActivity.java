package com.example.gotourapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gotourapplication.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
//Recycler VIEW
    ArrayList<String> dataSource;
    ArrayList<Integer> dataSource2;
    ArrayList<String> dataSource3;
    ArrayList<String> dataSource4;

    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {
        ArrayList<String> data;
        ArrayList<Integer> data2;
        ArrayList<String> data3;
        ArrayList<String> data4;

        public MyRvAdapter(ArrayList<String> data,ArrayList<Integer> data2,ArrayList<String> data3, ArrayList<String> data4) {
            this.data = data;
            this.data2 = data2;
            this.data3 = data3;
            this.data4 = data4;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.rv_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.tvTitle.setText(data.get(position));
            holder.imageRV.setImageResource(data2.get(position));
            holder.price.setText(data3.get(position));
            holder.location.setText(data4.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;
            ImageView imageRV;
            TextView price;
            TextView location;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvTitle);
                imageRV = itemView.findViewById(R.id.imageRV);
                price = itemView.findViewById(R.id.price);
                location = itemView.findViewById(R.id.location);
            }
        }

    }
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//set binding method
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//

        //Setting the data source
        dataSource = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.images_title)));

//        dataSource2 = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.images)));

        dataSource2 = new ArrayList<>();
        dataSource2.add(R.drawable.image_1);
        dataSource2.add(R.drawable.image_2);
        dataSource2.add(R.drawable.image_3);
        dataSource2.add(R.drawable.image_4);
        dataSource2.add(R.drawable.image_5);
        dataSource2.add(R.drawable.image_6);
        dataSource2.add(R.drawable.image_7);
        dataSource2.add(R.drawable.image_8);
        dataSource2.add(R.drawable.image_9);
        dataSource2.add(R.drawable.image_10);

        dataSource3 = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.images_prices)));

        dataSource4 = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.images_location)));

        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter = new MyRvAdapter(dataSource,dataSource2,dataSource3,dataSource4);
        binding.horizontalRv.setLayoutManager(linearLayoutManager);
        binding.horizontalRv.setAdapter(myRvAdapter);
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

