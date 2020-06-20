package com.example.covid_19apps.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.covid_19apps.API.ApiClient;
import com.example.covid_19apps.API.ApiInterface;
import com.example.covid_19apps.Chart.ChartResponse;
import com.example.covid_19apps.R;
import com.example.covid_19apps.Chart.Sales;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GrafikActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    float barSpace = 0.02f;
    float groupSpace = 0.3f;
    List<Sales> salesList = new ArrayList<>();
    List<String> products = new ArrayList<>();
    List<String> monthsList = new ArrayList<>();
    BarChart barChart;
    ProgressBar progressBar;
    Button switchChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik);
        barChart = findViewById(R.id.bar_chart);
        progressBar = findViewById(R.id.progress_circular);
        switchChart = findViewById(R.id.switchChart);
        switchChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChartData(switchChart.getText().toString());
            }
        });

        bottomNav();
    }

    private void bottomNav() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set selected home
        bottomNavigationView.setSelectedItemId(R.id.grafik);

        // item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.kasus:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.grafik:
                        return true;

                    case R.id.informasi:
                        startActivity(new Intent(getApplicationContext(), InformasiActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.bantuan:
                        startActivity(new Intent(getApplicationContext(), BantuanActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
    }

    private void getChartData(String type) {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ChartResponse> call = apiInterface.getChartResponse();
        call.enqueue(new Callback<ChartResponse>() {
            @Override
            public void onResponse(Call<ChartResponse> call, Response<ChartResponse> response) {
                progressBar.setVisibility(View.GONE);
                Gson gson = new GsonBuilder().create();
                TypeToken<List<Sales>> typeTokenSales = new TypeToken<List<Sales>>() {
                };
                TypeToken<List<String>> typeTokenProductsorMonths = new TypeToken<List<String>>() {
                };

                salesList.clear();
                salesList.addAll(gson.fromJson(gson.toJson(response.body().getSalesResponse()), typeTokenSales.getType()));
                products = gson.fromJson(gson.toJson(response.body().getProductsResponse()), typeTokenProductsorMonths.getType());
                monthsList = gson.fromJson(gson.toJson(response.body().getMonthsResponse()), typeTokenProductsorMonths.getType());
                Log.d(TAG, "onResponse: " + gson.toJson(monthsList));
                if (type.equals("Lihat Data Kasus per Bulan")) {
                    setBarChart(products, monthsList, "product");
                    switchChart.setText("Lihat Data per Kasus");
                } else if (type.equals("Lihat Data per Kasus")) {
                    setBarChart(monthsList, products, "month");
                    switchChart.setText("Lihat Data Kasus per Bulan");
                }
            }

            @Override
            public void onFailure(Call<ChartResponse> call, Throwable t) {
                Toast.makeText(GrafikActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void setBarChart(List<String> dataSets, List<String> groupList, String type) {
        int[] MyColors = {
                getResources().getColor(R.color.bar1),
                getResources().getColor(R.color.bar2),
                getResources().getColor(R.color.bar3),
                getResources().getColor(R.color.bar8),
        };

        ArrayList<BarDataSet> arrayList = new ArrayList<>();
        for (int i = 0; i < dataSets.size(); i++) {
            BarDataSet barDataSet = new BarDataSet(barEntries(dataSets.get(i), groupList, type), dataSets.get(i));
            barDataSet.setColor(MyColors[i]);
            barDataSet.setValueTextSize(6f);
            arrayList.add(barDataSet);
        }

        BarData barData = new BarData();
        for (int i = 0; i < arrayList.size(); i++) {
            barData.addDataSet(arrayList.get(i));
        }

        barData.setValueTextSize(7f);
        barChart.setData(barData);
        barChart.setPinchZoom(true);
        barChart.setDrawGridBackground(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(groupList));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(true);

        barData.setBarWidth(.02f);

        Legend legend = barChart.getLegend();
        legend.setTextSize(10);
        legend.setWordWrapEnabled(true);

        barChart.setVisibleXRangeMaximum(3);
        barChart.getXAxis().setAxisMinimum(0);

        float defaultBarWidth;
        int groupCount = groupList.size();

        defaultBarWidth = (1 - groupSpace) / arrayList.size() - barSpace;
        if (defaultBarWidth >= 0) {
            barData.setBarWidth(defaultBarWidth);
        }
        if (groupCount != -1) {
            barChart.getXAxis().setAxisMinimum(0);
            barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupList.size());
            barChart.getXAxis().setCenterAxisLabels(true);
        }

        if (barData.getDataSetCount() > 1) {
            barChart.groupBars(0, groupSpace, barSpace);
        }

        barChart.invalidate();


    }


    private List<BarEntry> barEntries(String groupName, List<String> groupList, String type) {
        ArrayList<BarEntry> arrayList = new ArrayList<>();
        int i = 0;
        while (i < groupList.size()) {
            for (Sales s : salesList) {
                if (type.equals("month")) {
                    if (s.getMonth().equals(groupName)) {
                        BarEntry barEntry = new BarEntry(i, s.getSales());
                        arrayList.add(barEntry);
                        i++;
                    }
                } else if (type.equals("product")) {
                    if (s.getProduct().equals(groupName)) {
                        BarEntry barEntry = new BarEntry(i, s.getSales());
                        arrayList.add(barEntry);
                        i++;
                    }
                }
            }
        }
        return arrayList;
    }
}