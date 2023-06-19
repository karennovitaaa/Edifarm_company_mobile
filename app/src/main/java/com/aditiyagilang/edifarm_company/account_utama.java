//package com.aditiyagilang.edifarm_company;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//public class account_utama extends AppCompatActivity {
//    Button btn_post, btn_like, btn_edit;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_account_utama);
//        btn_post = (Button) findViewById(R.id.buttonPosts);
//        btn_like = (Button) findViewById(R.id.buttonLikes);
//        btn_edit = (Button) findViewById(R.id.button_editprofil);
//        btn_post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                move(new post(), "post");
//            }
//        });
//        btn_like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                move(new post(), "post");
//            }
//        });
//        btn_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(account_utama.this, edit_profile.class);
//                startActivity(intent);
//            }
//        });
//    }
//    private void move(Fragment fragment, String tag) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentFrame);
//        if (currentFragment != null) {
//            fragmentTransaction.hide(currentFragment);
//        }
//        Fragment existingFragment = fragmentManager.findFragmentByTag(tag);
//        if (existingFragment != null) {
//            fragmentTransaction.show(existingFragment);
//        } else {
//            fragmentTransaction.add(R.id.fragmentFrame, fragment, tag);
//        }
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
//
//
//
//
//}
