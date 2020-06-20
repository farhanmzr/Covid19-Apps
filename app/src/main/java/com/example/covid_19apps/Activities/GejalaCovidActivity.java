package com.example.covid_19apps.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.covid_19apps.R;

public class GejalaCovidActivity extends AppCompatActivity {

    private ImageView ic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala_covid);

        ic_back = findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoinformasi = new Intent(GejalaCovidActivity.this, InformasiActivity.class);
                startActivity(gotoinformasi);
                finish();
            }
        });
    }
}