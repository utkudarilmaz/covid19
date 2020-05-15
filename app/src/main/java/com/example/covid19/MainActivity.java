package com.example.covid19;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.graphics.Typeface;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import covid19.Covid19;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.summaryMenu:
                        intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.worldMenu:
                        intent = new Intent(MainActivity.this, WorldActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.newsMenu:
                        Toast.makeText(MainActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
                        break;                }
                return true;
            }
        });

        TextView totalCase = findViewById(R.id.totalCase);
        String confirmed = Covid19.totalConfirmed();
        totalCase.setText(String.valueOf(confirmed));

        TextView totalDeath = findViewById(R.id.totalDeath);
        String deaths = Covid19.totalDeaths();
        totalDeath.setText(String.valueOf(deaths));

        TextView totalRecovered = findViewById(R.id.totalRecovered);
        String recovered = Covid19.totalRecovered();
        totalRecovered.setText(String.valueOf(recovered));

        TextView activeCase = findViewById(R.id.activeCase);
        String active = Covid19.totalActive();
        activeCase.setText(String.valueOf(active));

        Float deathsRate = Float.valueOf(deaths) / Float.valueOf(confirmed);
        Float recoveredRate = Float.valueOf(recovered) / Float.valueOf(confirmed);
        Float activeRate = Float.valueOf(active) / Float.valueOf(confirmed);


        pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.setDrawHoleEnabled(false);

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(recoveredRate, "Recovered"));
        entries.add(new PieEntry(activeRate, "Active"));
        entries.add(new PieEntry(deathsRate, "Deaths"));

        PieDataSet dataSet = new PieDataSet(entries, "Worlwide Covid19 Lookup");
        Description desc = new Description();
        desc.setText("Worldwide Covid19 deaths, active and recovered case rates");
        pieChart.setDescription(desc);

        PieData data = new PieData(dataSet);

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);


        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);
        Typeface tfLight;
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();

    }


    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Intent intent = new Intent(MainActivity.this, WorldActivity.class);
//            startActivity(intent);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
