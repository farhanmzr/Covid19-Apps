package com.example.covid_19apps.API;

import com.example.covid_19apps.Chart.ChartResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("chart.php")
    Call<ChartResponse> getChartResponse();

}
