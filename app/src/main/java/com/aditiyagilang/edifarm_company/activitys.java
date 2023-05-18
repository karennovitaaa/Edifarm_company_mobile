package com.aditiyagilang.edifarm_company;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aditiyagilang.edifarm_company.Biographical.Biographical;
import com.aditiyagilang.edifarm_company.Riwayat.Historys;
import com.aditiyagilang.edifarm_company.dashboardfixx.dashboardfix;
import com.aditiyagilang.edifarm_company.databinding.ActivityActivitysBinding;
import com.aditiyagilang.edifarm_company.session.Sesession_jenis;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

public class activitys extends AppCompatActivity {

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewAct);
        bottomNavigationView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        bottomNavigationView.getMenu().findItem(R.id.bottom_activitas).setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.bottom_dash:
                        Intent intent = new Intent(activitys.this, dashboardfix.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_riwayat:
                        Intent intent1 = new Intent(activitys.this, Historys.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_plus:
                        Intent intent2 = new Intent(activitys.this, Sesession_jenis.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_activitas:
                        Intent intent5 = new Intent(activitys.this, activitys.class);
                        startActivity(intent5);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_profil:
                        Intent intent4 = new Intent(activitys.this, Biographical.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }

                return true;
            }


        });


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