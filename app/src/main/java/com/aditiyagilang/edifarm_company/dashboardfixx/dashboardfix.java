package com.aditiyagilang.edifarm_company.dashboardfixx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.activitys;
import com.aditiyagilang.edifarm_company.databinding.ActivityDashboardfixBinding;
import com.aditiyagilang.edifarm_company.login;


public class dashboardfix extends AppCompatActivity {
    SesionManager sesionManager;
    ImageButton add, prof, history, homes;
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

        add = findViewById(R.id.add);
        prof = findViewById(R.id.prof);
        history = findViewById(R.id.history);
        homes = findViewById(R.id.homes);

        binding.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(dashboardfix.this, activitys.class);
                startActivity(intent3);

            }
        });
//        add.setOnClickListener(this);
//        prof.setOnClickListener(this);
//        history.setOnClickListener(this);
//        homes.setOnClickListener(this);
    }


//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


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

//    @Override
//    public void onClick(View view) {
//
//    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.homes:
//                Intent intent = new Intent(dashboardfix.this, dashboardfix.class);
//                startActivity(intent);
//                break;
//            case R.id.prof:
//                Intent intent1 = new Intent(dashboardfix.this, EditProfile.class);
//                startActivity(intent1);
//                break;
//            case R.id.add:
//                Intent intent2 = new Intent(dashboardfix.this, activitys.class);
//                startActivity(intent2);
//                break;
//            case R.id.history:
//                Intent intent3 = new Intent(dashboardfix.this, activitys.class);
//                startActivity(intent3);
//                break;
//        }
//    }
}