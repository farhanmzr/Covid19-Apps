package com.example.covid_19apps.Chart;

import com.google.gson.annotations.SerializedName;

public class Sales {
    @SerializedName("product")
    private String product;

    @SerializedName("month")
    private String month;

    @SerializedName("sales")
    private int sales;

    public String getProduct() {
        return product;
    }

    public String getMonth() {
        return month;
    }

    public int getSales() {
        return sales;
    }
}