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
    private ArrayList<listDataPost> listData;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);
        listView = rootView.findViewById(R.id.listview_post);
        listData = setData();
        listDataPostAdapter postAdapter = new listDataPostAdapter(getActivity(), listData);
        listView.setAdapter(postAdapter);
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long L) {
        listDataPost list = listData.get(position);
        // code for item click
    }

    private ArrayList<listDataPost> setData() {
        ArrayList<listDataPost> listData = new ArrayList<>();
        listData.add(new listDataPost("lusy123", "12-12-12", "yaallah pengen bukber", R.drawable.petanimurka));
        listData.add(new listDataPost("adit123", "12-12-14", "yaallah pengen sahur", R.drawable.petanimurka));
        listData.add(new listDataPost("karen123", "12-12-52", "yaallah pengen trawehh", R.drawable.petanimurka));
        listData.add(new listDataPost("andru123", "12-16-12", "yaallah pengen ke gereja", R.drawable.petanimurka));
        return listData;
    }
}
