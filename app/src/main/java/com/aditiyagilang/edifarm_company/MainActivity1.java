package com.aditiyagilang.edifarm_company;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity1 extends AppCompatActivity {
    ImageButton addButton, homeButton, profileButton;
    ImageView add, home, profile;
    int adddef, homedef, profiledef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        addButton = findViewById(R.id.add);
        homeButton = findViewById(R.id.home);
        profileButton = findViewById(R.id.profile);
        add = findViewById(R.id.add);
        home = findViewById(R.id.home);
        profile = findViewById(R.id.profile);
        adddef = R.drawable.icon_add; // menyimpan ID gambar asli
        homedef = R.drawable.home; //
        profiledef = R.drawable.icon_profile; //
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add:
                        // jika gambar saat ini bukan gambar baru, maka simpan ID gambar saat ini sebagai defaultImageId
                        if (add.getTag() == null || (int) add.getTag() != R.drawable.icon_add_klik) {
                            adddef = add.getDrawable() == null ? (int) add.getTag() : R.drawable.icon_add;
                        }
                        add.setImageDrawable(getResources().getDrawable(R.drawable.icon_add_klik));
                        add.setTag(R.drawable.icon_add_klik);
                        if (profile.getTag() != null && (int) profile.getTag() != profiledef) {
                            profile.setImageResource(profiledef);
                            profile.setTag(profiledef);
                        } else if (home.getTag() != null && (int) home.getTag() != homedef) {
                            home.setImageResource(homedef);
                            home.setTag(homedef);
                        }
                        break;
                    case R.id.home:
                        if (home.getTag() == null || (int) home.getTag() != R.drawable.icon_home_klik) {
                            homedef = home.getDrawable() == null ? (int) home.getTag() : R.drawable.home;
                        }
                        home.setImageDrawable(getResources().getDrawable(R.drawable.icon_home_klik));
                        home.setTag(R.drawable.icon_home_klik);
                        if (add.getTag() != null && (int) add.getTag() != adddef) {
                            add.setImageResource(adddef);
                            add.setTag(adddef);
                        }
                        if (profile.getTag() != null && (int) profile.getTag() != profiledef) {
                            profile.setImageResource(profiledef);
                            profile.setTag(profiledef);
                        }
                        break;
                    case R.id.profile:
                        if (profile.getTag() == null || (int) profile.getTag() != R.drawable.icon_profile_klik) {
                            profiledef = profile.getDrawable() == null ? (int) profile.getTag() : R.drawable.icon_profile;
                        }
                        profile.setImageDrawable(getResources().getDrawable(R.drawable.icon_profile_klik));
                        profile.setTag(R.drawable.icon_profile_klik);
                        if (add.getTag() != null && (int) add.getTag() != adddef) {
                            add.setImageResource(adddef);
                            add.setTag(adddef);
                        } else if (home.getTag() != null && (int) home.getTag() != homedef) {
                            home.setImageResource(homedef);
                            home.setTag(homedef);
                        }
                        break;
                    default:
                        break;
                }
            }
        };

        addButton.setOnClickListener(buttonClickListener);
        homeButton.setOnClickListener(buttonClickListener);
        profileButton.setOnClickListener(buttonClickListener);
    }

    @Override
    public void onBackPressed() {
        boolean isAllTagsNull = add.getTag() == null && home.getTag() == null && profile.getTag() == null;
        if (!isAllTagsNull) {
            if (add.getTag() != null && (int) add.getTag() != adddef) {
                add.setImageResource(adddef);
                add.setTag(adddef);
            }
            if (home.getTag() != null && (int) home.getTag() != homedef) {
                home.setImageResource(homedef);
                home.setTag(homedef);
            }
            if (profile.getTag() != null && (int) profile.getTag() != profiledef) {
                profile.setImageResource(profiledef);
                profile.setTag(profiledef);
            }
        } else {
            // Setiap ImageView dikembalikan ke gambar aslinya
            add.setImageResource(adddef);
            add.setTag(null);
            home.setImageResource(homedef);
            home.setTag(null);
            profile.setImageResource(profiledef);
            profile.setTag(null);
        }
    }

}