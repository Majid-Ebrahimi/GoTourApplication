package com.example.gotourapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gotourapplication.R;

import java.util.ArrayList;

public class SliderImageAdapter extends RecyclerView.Adapter<SliderImageAdapter.ViewHolder> {

    ArrayList<Integer> imagesArrayList;
    ArrayList<String> titlesArrayList;
    ArrayList<String> pricesArrayList;
    ArrayList<String> locationsArrayList;

    public SliderImageAdapter(ArrayList<Integer> imagesArrayList, ArrayList<String> titlesArrayList, ArrayList<String> pricesArrayList, ArrayList<String> locationsArrayList) {
        this.imagesArrayList = imagesArrayList;
        this.titlesArrayList = titlesArrayList;
        this.pricesArrayList = pricesArrayList;
        this.locationsArrayList = locationsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_card_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ImagesCardView.setImageResource(imagesArrayList.get(position));
        holder.TitlesTextView.setText(titlesArrayList.get(position));
        holder.PricesTextView.setText(pricesArrayList.get(position));
        holder.LocationsTextView.setText(locationsArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return imagesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ImagesCardView;
        TextView TitlesTextView;
        TextView PricesTextView;
        TextView LocationsTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImagesCardView = itemView.findViewById(R.id.ImagesCardView);
            TitlesTextView = itemView.findViewById(R.id.TitlesTextView);
            PricesTextView = itemView.findViewById(R.id.PricesTextView);
            LocationsTextView = itemView.findViewById(R.id.LocationsTextView);
        }
    }
}
