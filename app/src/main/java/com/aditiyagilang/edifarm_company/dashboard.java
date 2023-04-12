package com.aditiyagilang.edifarm_company;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.aditiyagilang.edifarm_company.model.model_dashboard;
import com.aditiyagilang.edifarm_company.design.dasboardAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private boolean mIsLiked = false;
SesionManager sesionManager;

    private ArrayList<model_dashboard> model_dashboards;

   BottomNavigationItemView bottomNavigationItemView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sesionManager = new SesionManager(dashboard.this);
        if (!sesionManager.isLogin()){
            movetoLogin();
        }
<<<<<<< HEAD
        setContentView(R.layout.activity_dashboard);

=======

//        bottomNavigationItemView = findViewById(R.id.bottomNavigationView);
>>>>>>> 2c1dda71483edfa9d4a8e07850cf52adfaefbeeb
//        bottomNavigationItemView.setSelected(R.id.dashboard);
//
//        bottomNavigationItemView.setOn


//        likeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!mIsLiked) {
//                    likeButton.setBackgroundResource(R.drawable.heart_button_field);
//                    mIsLiked = true;
//                    Toast.makeText(dashboard.this, "Liked", Toast.LENGTH_SHORT).show();
//                } else {
//                    likeButton.setBackgroundResource(R.drawable.heart_button);
//                    mIsLiked = false;
//                    Toast.makeText(dashboard.this, "Unliked", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        ListView listView = findViewById(R.id.listview);
        model_dashboards = setMedsosAndName();
        dasboardAdapter dasboardAdapter = new dasboardAdapter(dashboard.this, model_dashboards);
        listView.setAdapter(dasboardAdapter);
        listView.setOnItemClickListener(this);

//        ImageButton btnLike = findViewById(R.id.like_button);
//        btnLike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Ubah background image ke like_red.png
//                btnLike.setBackgroundResource(R.drawable.like_red);
//            }
//        });

    }

    private void movetoLogin() {
        Intent intent = new Intent(dashboard.this, login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    private ArrayList<model_dashboard> setMedsosAndName() {
        model_dashboards = new ArrayList<>();

        model_dashboards.add(new model_dashboard(R.drawable.post, "Wishal"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Andru_Gtg"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Karen_Solo"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Lussy_D"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Rey65"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));
        model_dashboards.add(new model_dashboard(R.drawable.post, "Facebook"));



        return model_dashboards;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        model_dashboard list = model_dashboards.get(position);
        Toast.makeText(dashboard.this, "Social Media Name ..." + list.getName(), Toast.LENGTH_SHORT).show();
    }

    public void like(View view){
        ImageButton likeButton = findViewById(R.id.like_button);
                likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsLiked) {
                    likeButton.setBackgroundResource(R.drawable.like_red);
                    mIsLiked = true;
                    Toast.makeText(dashboard.this, "Liked", Toast.LENGTH_SHORT).show();
                } else {
                    likeButton.setBackgroundResource(R.drawable.like_white);
                    mIsLiked = false;
                    Toast.makeText(dashboard.this, "Unliked", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void coment(View view){
        Intent intent = new Intent(dashboard.this,comen.class);

        startActivity(intent);
    }
    public void ck(View view){
        Intent intent = new Intent(dashboard.this,account_utama.class);

        startActivity(intent);
    }
}