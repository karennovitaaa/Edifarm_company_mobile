package com.aditiyagilang.edifarm_company.dashboardfixx;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aditiyagilang.edifarm_company.Biographical.Biographical;
import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.activitys;
import com.aditiyagilang.edifarm_company.databinding.ActivityDashboardfixBinding;
import com.aditiyagilang.edifarm_company.login;
import com.aditiyagilang.edifarm_company.session.Sesession_jenis;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class dashboardfix extends AppCompatActivity {

    ColorStateList def;
    TextView item1, item2, select;

    SesionManager sesionManager;
    ImageButton add, prof, history, homes;
    BottomNavigationView bottomNavigationView;
    private AppBarConfiguration appBarConfiguration;
    private ActivityDashboardfixBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardfixBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboardfix);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        sesionManager = new SesionManager(dashboardfix.this);
        if (!sesionManager.isLogin()) {
            movetoLogin();
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.bottom_dash:
                        Intent intent = new Intent(dashboardfix.this, dashboardfix.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.bottom_riwayat:
                        Intent intent1 = new Intent(dashboardfix.this, activitys.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.bottom_plus:
                        Intent intent2 = new Intent(dashboardfix.this, Sesession_jenis.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.bottom_activitas:
                        Intent intent5 = new Intent(dashboardfix.this, Biographical.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case R.id.bottom_profil:
                        Intent intent4 = new Intent(dashboardfix.this, Sesession_jenis.class);
                        startActivity(intent4);
                        finish();
                        break;
                }

                return true;
            }


        });
    }

    private void movetoLogin() {
        Intent intent = new Intent(dashboardfix.this, login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboardfix);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}