package com.aditiyagilang.edifarm_company.Biographical;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.Riwayat.Historys;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.activitys;
import com.aditiyagilang.edifarm_company.dashboardfixx.dashboardfix;
import com.aditiyagilang.edifarm_company.databinding.ActivityBiographicalBinding;
import com.aditiyagilang.edifarm_company.edit_profile;
import com.aditiyagilang.edifarm_company.login;
import com.aditiyagilang.edifarm_company.session.Sesession_jenis;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;


public class Biographical extends AppCompatActivity {
    ToggleButton posting;
    ToggleButton likes;
    ImageView profil;
    Button seting;
    BottomNavigationView bottomNavigationView;
    SesionManager sesionManager;

    private AppBarConfiguration appBarConfiguration;
    private ActivityBiographicalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBiographicalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomNavigationView = findViewById(R.id.bottomNavigationViewBio);
        setSupportActionBar(binding.toolbar); // Menambahkan ini untuk mengatur ActionBar
        sesionManager = new SesionManager(this);
        profil = findViewById(R.id.imageprofile);
        String url = "https://40a3-118-99-83-51.ngrok-free.app/";
        String fotoProfil = sesionManager.getUserDetail().get(SesionManager.PHOTO);
        String imageUrl = url + fotoProfil;
        Picasso.get().load(imageUrl).into(profil);
        bottomNavigationView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_biographical);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.ChangePass || destination.getId() == R.id.ChangeAcount) {
                getSupportActionBar().hide(); // Sembunyikan ActionBar
            } else {
                getSupportActionBar().show(); // Tampilkan ActionBar
            }
        });
        posting = findViewById(R.id.posting);
        likes = findViewById(R.id.likes);
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        binding.seting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Biographical.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fragment_menu_side);
                SesionManager sesionManager;
                sesionManager = new SesionManager(dialog.getContext());
                Button bio = dialog.findViewById(R.id.bio);

                Button logout = dialog.findViewById(R.id.logouts);

                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(Biographical.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.pop_up_logout);
                        SesionManager sesionManager = new SesionManager(dialog.getContext());
                        Button cancel = dialog.findViewById(R.id.cancel);
                        Button logout = dialog.findViewById(R.id.logouts);

                        logout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sesionManager.logoutSession();
                                Intent intent = new Intent(Biographical.this, login.class);

                                startActivity(intent);
                            }
                        });

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                navController.navigate(R.id.action_Pop_to_PostUser);
                            }
                        });


                        dialog.show();
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSett;
                        dialog.getWindow().setGravity(Gravity.CENTER);
                    }
                });
                bio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Biographical.this, edit_profile.class);
                        startActivity(intent);
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSett;
                dialog.getWindow().setGravity(Gravity.RIGHT | Gravity.TOP);
            }
        });

        binding.posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load(new PostingUser());
                likes.setPaintFlags(posting.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            }
        });

        binding.likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likes.setPaintFlags(likes.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                load(new GetPostUserA());
            }
        });

        bottomNavigationView.getMenu().findItem(R.id.bottom_profil).setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.bottom_dash:
                        Intent intent = new Intent(Biographical.this, dashboardfix.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_riwayat:
                        Intent intent1 = new Intent(Biographical.this, Historys.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_plus:
                        Intent intent2 = new Intent(Biographical.this, Sesession_jenis.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_activitas:
                        Intent intent5 = new Intent(Biographical.this, activitys.class);
                        startActivity(intent5);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.bottom_profil:
                        Intent intent4 = new Intent(Biographical.this, Biographical.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }

                return true;
            }


        });
    }


    private void load(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.cloude, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_biographical);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
