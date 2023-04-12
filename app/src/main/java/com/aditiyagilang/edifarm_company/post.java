package com.aditiyagilang.edifarm_company;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.aditiyagilang.edifarm_company.design.listDataPostAdapter;
import com.aditiyagilang.edifarm_company.model.listDataPost;

import java.util.ArrayList;

public class post extends Fragment implements AdapterView.OnItemClickListener {
    private  ArrayList<listDataPost> listData;
    ListView listView;

    public post() {
        listView = listView.findViewById(R.id.listview_post);
        listData = setData();
        listDataPostAdapter postAdapter = new listDataPostAdapter(post.this, listData);
        listView.setAdapter(postAdapter);
        listView.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?>adapterView, View view, int position, long L) {
        listDataPost list = listData.get(position);


    }

    private ArrayList<listDataPost> setData() {
        listData = new ArrayList<>();
        listData.add(new listDataPost("lusy123", "12-12-12", "yaallah pengen bukber", R.drawable.petanimurka));
        listData.add(new listDataPost("adit123", "12-12-14", "yaallah pengen sahur", R.drawable.petanimurka));
        listData.add(new listDataPost("karen123", "12-12-52", "yaallah pengen trawehh", R.drawable.petanimurka));
        listData.add(new listDataPost("andru123", "12-16-12", "yaallah pengen ke gereja", R.drawable.petanimurka));
    return listData;
    }
}



