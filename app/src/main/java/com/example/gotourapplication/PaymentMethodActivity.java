package com.example.gotourapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.gotourapplication.Adapter.PaymentMethodAdapter;
import com.example.gotourapplication.databinding.ActivityPaymentMethodBinding;

import java.util.ArrayList;

public class PaymentMethodActivity extends AppCompatActivity {

    private ActivityPaymentMethodBinding binding;

    ArrayList<String> bankNameSource;
    ArrayList<String> cardNumberSource;
    ArrayList<String> bankBalanceSource;
    ArrayList<String> expDateSource;
    ArrayList<String> bankAccountSource;

    LinearLayoutManager linearLayoutManager;
    PaymentMethodAdapter paymentMethodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityPaymentMethodBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        //back button animation and set onClick for direct to main activity
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake_animation);
        binding.PaymentMethodBackButton.setOnClickListener(view ->{
            onBackPressed();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            binding.PaymentMethodBackButton.startAnimation(shake);
        });
//

        bankNameSource = new ArrayList<>();
        bankNameSource.add("Saman Bank");
        bankNameSource.add("Mellat Bank");

        cardNumberSource = new ArrayList<>();
        cardNumberSource.add("6219861915420789");
        cardNumberSource.add("6104337541343131");

        bankBalanceSource = new ArrayList<>();
        bankBalanceSource.add("$14 000");
        bankBalanceSource.add("$700");

        expDateSource =  new ArrayList<>();
        expDateSource.add("9/24");
        expDateSource.add("12/23");

        bankAccountSource = new ArrayList<>();
        bankAccountSource.add(getResources().getString(R.string.sample_name_string));
        bankAccountSource.add(getResources().getString(R.string.sample_name_string));

        linearLayoutManager = new LinearLayoutManager(PaymentMethodActivity.this, LinearLayoutManager.HORIZONTAL, false);
        paymentMethodAdapter = new PaymentMethodAdapter(bankNameSource,cardNumberSource,bankBalanceSource,expDateSource,bankAccountSource);

        binding.PaymentCardRV.setLayoutManager(linearLayoutManager);
        binding.PaymentCardRV.setAdapter(paymentMethodAdapter);

    }
}