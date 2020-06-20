package com.example.covid_19apps.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.covid_19apps.R;

public class LandingPage1Activity extends AppCompatActivity {

    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.covid_19apps.R.layout.activity_landing_page1);

        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotonextlandingpage = new Intent(LandingPage1Activity.this, LandingPage2Activity.class);
                startActivity(gotonextlandingpage);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }
}