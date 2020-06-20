package com.example.covid_19apps.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.covid_19apps.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BantuanActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout telepon, sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);

        telepon = findViewById(R.id.telepon);
        sms = findViewById(R.id.sms);

        telepon.setOnClickListener(this);
        sms.setOnClickListener(this);
        bottomNav();
    }

    private void bottomNav() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set selected home
        bottomNavigationView.setSelectedItemId(R.id.bantuan);

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
                        startActivity(new Intent(getApplicationContext(), GrafikActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.informasi:
                        startActivity(new Intent(getApplicationContext(), InformasiActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.bantuan:
                        return true;
                }

                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == telepon) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:119"));
            startActivity(intent);
        }

        if (v == sms) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "081212123119", null));
            intent.putExtra("smsbody", "Hello Pusat Informasi Covid19...");
            startActivity(intent);
        }
    }
}