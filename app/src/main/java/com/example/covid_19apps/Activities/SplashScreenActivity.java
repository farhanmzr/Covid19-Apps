package com.example.covid_19apps.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.covid_19apps.R;

public class SplashScreenActivity extends AppCompatActivity {

    private int waktu_loading = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(SplashScreenActivity.this, LandingPage1Activity.class);
                startActivity(home);
                finish();
                overridePendingTransition(0, 0);
                getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        }, waktu_loading);
    }
}