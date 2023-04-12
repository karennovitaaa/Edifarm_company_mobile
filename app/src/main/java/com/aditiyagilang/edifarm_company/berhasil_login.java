package com.aditiyagilang.edifarm_company;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class berhasil_login extends AppCompatActivity  {
    Button logout;
    SesionManager sesionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil_login);
//        logout = findViewById(R.id.logout1);
//        sesionManager = new SesionManager(berhasil_login.this);
//        if (!sesionManager.isLogin()){
//            movetoLogin();
//        }
//        logout.setOnClickListener(this);

    }
//    private void movetoLogin() {
//        Intent intent = new Intent(berhasil_login.this, login.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
//        startActivity(intent);
//        finish();
//    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.logout1:
//
//                logout();
//                break;
//
//
//        }
    }
//    public void logout(){
//        sesionManager.logoutSession();
//        movetoLogin();
//    }

