package com.example.gotourapplication.Adapter;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gotourapplication.PaymentMethodActivity;
import com.example.gotourapplication.R;

import java.util.ArrayList;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> {

    ArrayList<String> bankNameArrayList;
    ArrayList<String> cardNumberArrayList;
    ArrayList<String> bankBalanceArrayList;
    ArrayList<String> expDateArrayList;
    ArrayList<String> bankAccountArrayList;

    public PaymentMethodAdapter(ArrayList<String> bankNameArrayList, ArrayList<String> cardNumberArrayList, ArrayList<String> bankBalanceArrayList, ArrayList<String> expDateArrayList, ArrayList<String> bankAccountArrayList) {
        this.bankNameArrayList = bankNameArrayList;
        this.cardNumberArrayList = cardNumberArrayList;
        this.bankBalanceArrayList = bankBalanceArrayList;
        this.expDateArrayList = expDateArrayList;
        this.bankAccountArrayList = bankAccountArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_method_card_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.CardTypeTV.setText(bankNameArrayList.get(position));
        holder.CardNumberTV.setText(cardNumberArrayList.get(position));
        holder.CardBalanceTV.setText(bankBalanceArrayList.get(position));
        holder.EXPDateTV.setText(expDateArrayList.get(position));
        holder.CardNameTV.setText(bankAccountArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return cardNumberArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CardTypeTV;
        TextView CardNumberTV;
        TextView CardBalanceTV;
        TextView EXPDateTV;
        TextView CardNameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CardTypeTV = itemView.findViewById(R.id.CardTypeTV);
            CardNumberTV = itemView.findViewById(R.id.CardNumberTV);
            CardBalanceTV = itemView.findViewById(R.id.CardBalanceTV);
            EXPDateTV = itemView.findViewById(R.id.EXPDateTV);
            CardNameTV = itemView.findViewById(R.id.CardNameTV);
        }
    }
}
