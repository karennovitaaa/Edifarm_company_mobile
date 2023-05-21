package com.aditiyagilang.edifarm_company.dashboardfixx;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aditiyagilang.edifarm_company.Biographical.Biographical;
import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.Riwayat.Historys;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.activitys;

import com.aditiyagilang.edifarm_company.databinding.ActivityDashboardfixBinding;
import com.aditiyagilang.edifarm_company.login;
import com.aditiyagilang.edifarm_company.session.Sesession_jenis;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class dashboardfix extends AppCompatActivity {

    ColorStateList def;
    Button item1, act, selecte;

    SesionManager sesionManager;
    ImageButton add, prof, history, homes;
    BottomNavigationView bottomNavigationView;
    private AppBarConfiguration appBarConfiguration;
    private ActivityDashboardfixBinding binding;


// ...


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
        bottomNavigationView.getMenu().findItem(R.id.bottom_dash).setChecked(true);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.bottom_dash:
                        Intent intent = new Intent(dashboardfix.this, dashboardfix.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_riwayat:
                        Intent intent1 = new Intent(dashboardfix.this, Historys.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_plus:
                        Intent intent2 = new Intent(dashboardfix.this, Sesession_jenis.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_activitas:
                        Intent intent5 = new Intent(dashboardfix.this, activitys.class);
                        startActivity(intent5);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_profil:
                        Intent intent4 = new Intent(dashboardfix.this, Biographical.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }

                return true;
            }
        });

        binding.selecte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load(new FirstFragment());
                binding.selecte.setBackgroundColor(R.drawable.back_tablayout);
                binding.selecte.setTextColor(getResources().getColor(R.color.white));
                binding.act.setBackgroundColor(getResources().getColor(R.color.white));
                binding.act.setTextColor(R.drawable.white_tablayout);
            }
        });

        binding.act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load(new ActivityPosts());
                binding.selecte.setBackgroundColor(R.drawable.white_tablayout);
                binding.selecte.setTextColor(getResources().getColor(R.color.white));
                binding.act.setBackgroundColor(getResources().getColor(R.color.backgroun));
                binding.act.setTextColor(R.drawable.back_tablayout);
            }
        });


        // Tambahkan kode berikut
        View view = getWindow().getDecorView().getRootView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
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

    private void load(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dashu, fragment);
        fragmentTransaction.commit();
    }


}