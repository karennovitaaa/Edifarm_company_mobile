package com.aditiyagilang.edifarm_company.Riwayat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aditiyagilang.edifarm_company.Biographical.Biographical;
import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.activitys;
import com.aditiyagilang.edifarm_company.dashboardfixx.dashboardfix;
import com.aditiyagilang.edifarm_company.databinding.ActivityHistoryBinding;
import com.aditiyagilang.edifarm_company.session.Sesession_jenis;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Historys extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private AppBarConfiguration appBarConfiguration;
    private ActivityHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomNavigationView = findViewById(R.id.bottomNavigationViewHis);

        bottomNavigationView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_history);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        bottomNavigationView.getMenu().findItem(R.id.bottom_riwayat).setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.bottom_dash:
                        Intent intent = new Intent(Historys.this, dashboardfix.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_riwayat:
                        Intent intent1 = new Intent(Historys.this, Historys.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_plus:
                        Intent intent2 = new Intent(Historys.this, Sesession_jenis.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_activitas:
                        Intent intent5 = new Intent(Historys.this, activitys.class);
                        startActivity(intent5);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_profil:
                        Intent intent4 = new Intent(Historys.this, Biographical.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }

                return true;
            }


        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_history);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}