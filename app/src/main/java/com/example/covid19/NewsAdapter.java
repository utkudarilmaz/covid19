package com.example.covid19;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    ArrayList<NewsModel> news;
    Context context;

    NewsAdapter(Context context, List<NewsModel> news){
        this.context = context;
        this.news = (ArrayList<NewsModel>) news;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView textViewHeader;
        TextView textViewDescription;
        ImageView imageView;
        Button button;


        NewsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            this.textViewHeader = (TextView) itemView.findViewById(R.id.header);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.description);
            this.imageView = (ImageView) itemView.findViewById(R.id.image);
            this.button = (Button) itemView.findViewById(R.id.button);

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
        holder.textViewDescription.setText(news.get(i).description);
        Drawable d = null;
        try {
            InputStream is = (InputStream) new URL(news.get(i).image).getContent();
            d = Drawable.createFromStream(is, "src name");

        } catch (Exception e) {
            System.err.println("image convert error");
        }
        holder.imageView.setImageDrawable(d);

        holder.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(news.get(i).url));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}