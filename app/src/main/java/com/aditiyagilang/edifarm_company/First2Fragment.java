package com.aditiyagilang.edifarm_company;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;


import com.aditiyagilang.edifarm_company.databinding.FragmentFirst2Binding;
import com.aditiyagilang.edifarm_company.design.ActivityAdapter;
import com.aditiyagilang.edifarm_company.model.activity.Activity;
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;
import com.aditiyagilang.edifarm_company.design.ActivityAdapter.OnItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class First2Fragment extends Fragment implements View.OnClickListener, OnItemClickListener {
    SesionManager sesionManager;
    private FragmentFirst2Binding binding;
    TextView activitys_name;
    Button status;
    ImageButton addAct;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirst2Binding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    RecyclerView recyclerView;
    ActivityDataItem activityDataItem;
    LinearLayoutManager linearLayoutManager;

    ApiInterface apiInterface;

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activitys_name = getView().findViewById(R.id.nama_kegiatan);
        status = getView().findViewById(R.id.klaim_k);
        sesionManager = new SesionManager(requireContext());
addAct = getView().findViewById(R.id.addAct);
        recyclerView = getView().findViewById(R.id.tvlist_activity);
        linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String id = sesionManager.getUserDetail().get(SesionManager.ID);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Activity> ActCall = apiInterface.actResponse(id);

        ActCall.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    List<ActivityDataItem> activityDataItemList = response.body().getData();
                    ActivityAdapter activityAdapter = new ActivityAdapter(getContext(), activityDataItemList, First2Fragment.this);

                    recyclerView.setAdapter(activityAdapter);
                    activityDataItem = activityDataItemList.get(0);
                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
                t.printStackTrace();
            }
        });

//        status.setOnClickListener(this);
        addAct.setOnClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    String Id;
    String Id_Kegiatan, Status;


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.klaim_k:
                Id = sesionManager.getUserDetail().get(SesionManager.ID);
                Id_Kegiatan = String.valueOf(activityDataItem.getId());
                Status = "selesai";
                klaim(Id, Id_Kegiatan, Status);
                break;

            case R.id.addAct:
//                NavHostFragment.findNavController(First2Fragment.this)
//                        .navigate(R.id.action_First2Fragment_to_Second2Fragment);
                showDialog();
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_second2);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    private void klaim(String id, String id_Kegiatan, String status) {
        Call<Activity> UpActCall = apiInterface.UpactResponse(id, id_Kegiatan, status);
        UpActCall.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Toast.makeText(getContext(), response.body().getMessage() + "Mari Gess", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), response.body().getMessage() + "Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {

            }
        });
    }


    public void onItemClick(ActivityDataItem activityDataItem) {
        this.activityDataItem = activityDataItem;
    }

    @Override
    public void onItemClick(ActivityAdapter adapter, View view, int position, ActivityDataItem item) {

    }

    @Override
    public void onStatusClick(ActivityAdapter adapter, View view, int position, ActivityDataItem item) {

    }



}

