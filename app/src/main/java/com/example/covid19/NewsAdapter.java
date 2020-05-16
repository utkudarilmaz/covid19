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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    ArrayList<NewsModel> news;
    NewsAdapter(List<NewsModel> news){
        this.news = (ArrayList<NewsModel>) news;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView textViewHeader;
        TextView textViewDescription;


        NewsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            this.textViewHeader = (TextView) itemView.findViewById(R.id.header);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.description);

        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_layout, viewGroup, false);
        NewsViewHolder nvh = new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int i) {
        holder.textViewHeader.setText(news.get(i).name);
        holder.textViewDescription.setText(String.valueOf(news.get(i).description));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

}