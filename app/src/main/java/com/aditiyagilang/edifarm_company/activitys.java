package com.aditiyagilang.edifarm_company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aditiyagilang.edifarm_company.EditProfile.EditProfile;
import com.aditiyagilang.edifarm_company.dashboardfixx.dashboardfix;
import com.aditiyagilang.edifarm_company.databinding.ActivityActivitysBinding;
import com.google.android.material.snackbar.Snackbar;

public class activitys extends AppCompatActivity implements View.OnClickListener {

    ImageButton add, prof, history, homes;
    private AppBarConfiguration appBarConfiguration;
    private ActivityActivitysBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityActivitysBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activitys);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        add = findViewById(R.id.add);
        prof = findViewById(R.id.prof);
        history = findViewById(R.id.history);
        homes = findViewById(R.id.homes);


        add.setOnClickListener(this);
        prof.setOnClickListener(this);
        history.setOnClickListener(this);
        homes.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activitys);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homes:
                Intent intent = new Intent(activitys.this, dashboardfix.class);
                startActivity(intent);
                break;
            case R.id.prof:
                Intent intent1 = new Intent(activitys.this, EditProfile.class);
                startActivity(intent1);
                break;
            case R.id.add:
                Intent intent2 = new Intent(activitys.this, activitys.class);
                startActivity(intent2);
                break;
            case R.id.history:
                Intent intent3 = new Intent(activitys.this, activitys.class);
                startActivity(intent3);
                break;
        }
    }
}