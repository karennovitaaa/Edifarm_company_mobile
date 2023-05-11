package com.aditiyagilang.edifarm_company.dashboardfixx;

import android.content.Intent;
import android.os.Bundle;


import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.databinding.ActivityDashboardfixBinding;
import com.aditiyagilang.edifarm_company.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aditiyagilang.edifarm_company.R;


public class dashboardfix extends AppCompatActivity {
    SesionManager sesionManager;
    private AppBarConfiguration appBarConfiguration;
    private ActivityDashboardfixBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardfixBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboardfix);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        sesionManager = new SesionManager(dashboardfix.this);
        if (!sesionManager.isLogin()) {
            movetoLogin();
        }


//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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