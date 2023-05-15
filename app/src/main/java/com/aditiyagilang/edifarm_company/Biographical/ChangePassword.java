package com.aditiyagilang.edifarm_company.Biographical;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.aditiyagilang.edifarm_company.R;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setBackgroundDrawable(null);
        }
    }
}