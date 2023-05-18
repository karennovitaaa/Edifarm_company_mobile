package com.aditiyagilang.edifarm_company;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aditiyagilang.edifarm_company.dashboardfixx.dashboardfix;
import com.aditiyagilang.edifarm_company.databinding.ActivityActivitysBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

public class activitys extends bottom_navbar {

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
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.First2Fragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//
//                switch (item.getItemId()) {
//                    case R.id.bottom_dash:
//                        startActivity(new Intent(activitys.this, dashboardfix.class));
//                        finish();
//                        bottomNavigationView.setItemBackgroundResource(R.drawable.selected_bottombar);
//                        item.setChecked(true);
//                        overridePendingTransition(0, 0);
//                        return true;
//
//                    case R.id.bottom_riwayat:
//                        startActivity(new Intent(activitys.this, dashboardfix.class));
//                        finish();
//                        bottomNavigationView.setItemBackgroundResource(R.drawable.unselected_botombar);
//                        item.setChecked(true);
//                        overridePendingTransition(0, 0);
//                        return true;
//
//                    case R.id.bottom_plus:
//                        bottomNavigationView.setItemBackgroundResource(R.drawable.unselected_botombar);
//                        item.setChecked(true);
//                        overridePendingTransition(0, 0);
//                        return true;
//
//                    case R.id.bottom_activitas:
//                        startActivity(new Intent(activitys.this, activitys.class));
//                        finish();
//                        bottomNavigationView.setItemBackgroundResource(R.drawable.selected_bottombar);
//                        item.setChecked(true);
//                        overridePendingTransition(0, 0);
//                        return true;
//
//                    case R.id.bottom_profil:
//                        startActivity(new Intent(activitys.this, dashboardfix.class));
//                        finish();
//                        bottomNavigationView.setItemBackgroundResource(R.drawable.unselected_botombar);
//                        item.setChecked(true);
//                        overridePendingTransition(0, 0);
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//        });


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


//        add = findViewById(R.id.add);
//        prof = findViewById(R.id.prof);
//        history = findViewById(R.id.history);
//        homes = findViewById(R.id.homes);


//        add.setOnClickListener(this);
//        prof.setOnClickListener(this);
//        history.setOnClickListener(this);
//        homes.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activitys);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}