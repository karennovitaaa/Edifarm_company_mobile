package com.aditiyagilang.edifarm_company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class berhasil_login extends AppCompatActivity {
    SesionManager sesionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil_login);
        sesionManager = new SesionManager(berhasil_login.this);
        if (!sesionManager.isLogin()){
            movetoLogin();
        }

    }
    private void movetoLogin() {
        Intent intent = new Intent(berhasil_login.this, login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}