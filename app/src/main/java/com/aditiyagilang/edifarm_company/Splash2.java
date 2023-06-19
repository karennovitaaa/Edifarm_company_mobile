package com.aditiyagilang.edifarm_company;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.aditiyagilang.edifarm_company.dashboardfixx.dashboardfix;

public class Splash2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash2.this, dashboardfix.class));
                finish();
            }
        }, 3000);
        
    }
}
