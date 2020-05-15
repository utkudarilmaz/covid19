package com.example.covid19;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    ArrayList<CountryModel> countries;
    CountryAdapter(List<CountryModel> countries){
        this.countries = (ArrayList<CountryModel>) countries;
    }


    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView textViewLocation;
        TextView textViewTotal;
        TextView textViewRecovered;
        TextView textViewDeaths;
        TextView textViewActive;

        CountryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            this.textViewLocation = (TextView) itemView.findViewById(R.id.location);
            this.textViewTotal = (TextView) itemView.findViewById(R.id.total);
            this.textViewRecovered = (TextView) itemView.findViewById(R.id.recovered);
            this.textViewDeaths = (TextView) itemView.findViewById(R.id.deaths);
            this.textViewActive = (TextView) itemView.findViewById(R.id.active);
        }
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards_layout, viewGroup, false);
        CountryViewHolder cvh = new CountryViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int i) {
        holder.textViewLocation.setText(countries.get(i).location);
        holder.textViewTotal.setText(String.valueOf(countries.get(i).total));
        holder.textViewRecovered.setText(String.valueOf(countries.get(i).recovered));
        holder.textViewDeaths.setText(String.valueOf(countries.get(i).deaths));
        holder.textViewActive.setText(String.valueOf(countries.get(i).active));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

}