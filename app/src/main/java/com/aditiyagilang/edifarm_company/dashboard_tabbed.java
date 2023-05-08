package com.aditiyagilang.edifarm_company;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class dashboard_tabbed extends AppCompatActivity implements View.OnClickListener{

    ColorStateList def;
    TextView item_feeds, item_act, select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_tabbed2);

        Toolbar toolbar = findViewById(R.id.toolbar_tab);
        setSupportActionBar(toolbar);
        item_feeds = findViewById(R.id.item_feeds);
        item_act = findViewById(R.id.item_act);

        item_feeds.setOnClickListener(this);
        item_act.setOnClickListener(this);

        select = findViewById(R.id.select);
        def = item_act.getTextColors();




    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.item_feeds){
            select.animate().x(0).setDuration(100);
            item_feeds.setTextColor(Color.WHITE);
            item_act.setTextColor(def);

            Fragment fragment = new post();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_dash, fragment);
            fragmentTransaction.commit();

        } else if (v.getId() == R.id.item_act){
            item_feeds.setTextColor(def);
            item_act.setTextColor(Color.WHITE);
            int size = item_act.getWidth();
            select.animate().x(size).setDuration(100);

            Fragment fragment = new post();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_dash, fragment);
            fragmentTransaction.commit();

        }

    }
}