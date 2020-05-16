package com.example.covid19;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<NewsModel> news;
    private static String DEFAULT_CHANNEL_ID = "default_channel";
    private static String DEFAULT_CHANNEL_NAME = "Default";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Context context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        try {
            news = (ArrayList<NewsModel>) Fetcher.fetchNews();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new NewsAdapter(context, news);
        recyclerView.setAdapter(adapter);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel(mNotificationManager);

        Notification notification = new NotificationCompat.Builder(this, "default_channel")
                .setContentTitle("Haberler")   //Set the title of Notification
                .setContentText("Covid19 ile ilgili yeni haberleri kacirma!")
                .setSmallIcon(R.drawable.notification).build();

        mNotificationManager.notify(1, notification);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.summaryMenu:
                        intent = new Intent(NewsActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.worldMenu:
                        intent = new Intent(NewsActivity.this, WorldActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.newsMenu:
                        intent = new Intent(NewsActivity.this, NewsActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

    }

    public static void createNotificationChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(DEFAULT_CHANNEL_ID) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(
                        DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
        }
    }

}
