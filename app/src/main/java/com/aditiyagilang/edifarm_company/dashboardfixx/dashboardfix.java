package com.aditiyagilang.edifarm_company.dashboardfixx;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.Biographical.Biographical;
import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.Riwayat.Historys;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.activitys;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.ActivityDashboardfixBinding;
import com.aditiyagilang.edifarm_company.login;
import com.aditiyagilang.edifarm_company.model.SearchingUser.SearchingUserDataItem;
import com.aditiyagilang.edifarm_company.session.Sesession_jenis;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class dashboardfix extends AppCompatActivity {

    ColorStateList def;
    Button item1, act, selecte;

    SesionManager sesionManager;
    ImageButton add, prof, history, homes;
    BottomNavigationView bottomNavigationView;
    SearchView searching;
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;
    Dialog dialog;
    Context context;

    ApiInterface apiInterface;
    SearchingUserDataItem searchingUserDataItem;
    private AppBarConfiguration appBarConfiguration;
    private ActivityDashboardfixBinding binding;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardfixBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        searching = findViewById(R.id.action_search);

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboardfix);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        sesionManager = new SesionManager(dashboardfix.this);
        if (!sesionManager.isLogin()) {
            movetoLogin();
        }
        bottomNavigationView.getMenu().findItem(R.id.bottom_dash).setChecked(true);
// ... Rest of the code


        apiInterface = ApiClient.getClient().create(ApiInterface.class);

//        searching.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String search) {
//                search = searching.getQuery().toString();
//                final Dialog dialog = new Dialog(dashboardfix.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.fragment_list_user);
//                RecyclerView dialogRecyclerView = dialog.findViewById(R.id.list_user);
//                LinearLayoutManager dialogLinearLayoutManager = new LinearLayoutManager(dialog.getContext());
//                dialogRecyclerView.setLayoutManager(dialogLinearLayoutManager);
//
//                Call<SearchibUser> dashCall = apiInterface.getPostsByUsernameResponse(search);
//                dashCall.enqueue(new Callback<SearchibUser>() {
//                    @Override
//                    public void onResponse(Call<SearchibUser> call, Response<SearchibUser> response) {
//                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
//                            List<SearchingUserDataItem> searchingUserDataItems = response.body().getData();
//                            SearchingAcount searchingAcount = new SearchingAcount(dashboardfix.this, searchingUserDataItems, new SearchingAcount.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(SearchingAcount adapter, View view, int position, SearchingUserDataItem item) {
//
//                                }
//
//                                @Override
//                                public void onStatusClick(SearchingAcount adapter, View view, int position, SearchingUserDataItem item) {
//
//                                }
//                            });
//
//                            dialogRecyclerView.setAdapter(searchingAcount);
//                            searchingUserDataItem = searchingUserDataItems.get(0);
//                        } else {
//                            final Dialog dialog = new Dialog(dashboardfix.this);
//                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                            dialog.setContentView(R.layout.pop_up_failed);
//                            Button failed = dialog.findViewById(R.id.failed);
//                            TextView massage = dialog.findViewById(R.id.massegedone);
//                            massage.setText("Tidak Ada User");
//                            failed.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    dialog.dismiss();
//                                }
//                            });
//                            dialog.show();
//                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
//                            dialog.getWindow().setGravity(Gravity.CENTER);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<SearchibUser> call, Throwable t) {
//                        // Implementasikan tindakan saat permintaan gagal di sini
//                    }
//                });
//
//                dialog.show();
//                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//                dialog.getWindow().setGravity(Gravity.BOTTOM);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//
//            // ... Rest of the code
//        });


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
        binding.selecte.setBackgroundColor(Color.WHITE);
        binding.selecte.setTextColor(Color.BLACK);
        binding.act.setBackgroundColor(Color.TRANSPARENT);
        binding.act.setTextColor(Color.WHITE);
        binding.selecte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load(new FirstFragment());
                binding.selecte.setBackgroundColor(Color.WHITE);
                binding.selecte.setTextColor(Color.BLACK);
                binding.act.setBackgroundColor(Color.TRANSPARENT);
                binding.act.setTextColor(Color.WHITE);
            }
        });

        binding.act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load(new ActivityPosts());
                binding.selecte.setBackgroundColor(Color.TRANSPARENT);
                binding.selecte.setTextColor(Color.WHITE);
                binding.act.setBackgroundColor(Color.WHITE);
                binding.act.setTextColor(Color.BLACK);
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