package com.example.covid_19apps.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.covid_19apps.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InformasiActivity extends AppCompatActivity {

    private TextView tv_lihat_semua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);

        tv_lihat_semua = findViewById(R.id.tv_lihat_semua);
        tv_lihat_semua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotogejala = new Intent(InformasiActivity.this, GejalaCovidActivity.class);
                startActivity(gotogejala);
                finish();
            }
        });

        bottomNav();
    }

    private void bottomNav() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set selected home
        bottomNavigationView.setSelectedItemId(R.id.informasi);

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
}