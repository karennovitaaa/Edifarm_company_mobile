package com.aditiyagilang.edifarm_company.Biographical;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.squareup.picasso.Picasso;

public class ChangeAcount extends AppCompatActivity {
    String url = "https://af53-103-160-182-11.ngrok-free.app/";
    ImageView profil;
    SesionManager sesionManager;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_change_acount);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setBackgroundDrawable(null);
        }
        sesionManager = new SesionManager(ChangeAcount.this);
        String fotoprof = sesionManager.getUserDetail().get(SesionManager.PHOTO);
        String ImgProfil = url + fotoprof;
        profil = (ImageView) findViewById(R.id.imageprofs);
        Toast.makeText(this, fotoprof, Toast.LENGTH_SHORT).show();
        Picasso.get().load(ImgProfil).into(profil);


    }
}