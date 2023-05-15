package com.aditiyagilang.edifarm_company.Biographical;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.aditiyagilang.edifarm_company.R;

public class ChangeAcount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_change_acount);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setBackgroundDrawable(null);
        }
    }
}