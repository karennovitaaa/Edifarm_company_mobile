package com.aditiyagilang.edifarm_company;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.ImageButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.FragmentSecond2Binding;

import com.aditiyagilang.edifarm_company.model.activity.Activity;
import com.aditiyagilang.edifarm_company.model.addActivity.AddActivity;

import okhttp3.Response;


public class Second2Fragment extends Fragment {

    private FragmentSecond2Binding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecond2Binding.inflate(inflater, container, false);
        return binding.getRoot();

    }

//    public void showDatePickerDialog(View v) {
//        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
//                new DatePickerDialog.OnDateSetListener() {
//    }



//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(Second2Fragment.this)
//                        .navigate(R.id.action_Second2Fragment_to_First2Fragment);
//            }
//        });
//    }

//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//addActivity = getView().findViewById(R.id.button_second);
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//
//
//        addActivity.setOnClickListener(this);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.button_second:
//            Activity_Name = "Nyalon c Lurah";
//            Status = "belum";
//            Start = "2004-11-11";
//            End = "2005-11-11";
//            User_Id = "5";
//            Creat(Activity_Name, Status, Start, End, User_Id);
//        }
    }

//    private void Creat(
//            String activity_Name, String status, String start, String end, String user_Id
//    ) {
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<AddActivity> addCall = apiInterface.CreatActResponse(activity_Name, status, start, end, user_Id);
//addCall.enqueue(new Call.Callback<AddActivity>() {
//    @Override
//    public void onResponse(Call<AddActivity> call, Response<AddActivity> response) {
//        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
//            Toast.makeText(getContext(), response.body().getMessage() + "Mari Gess", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getContext(), response.body().getMessage() + "Salah", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onFailure(Call<AddActivity> call, Throwable t) {
//        Toast.makeText(getContext(), "Gagal terhubung ke server" + t.toString(), Toast.LENGTH_SHORT).show();
//        Log.d("SERVER!" , t.toString());
//    }
//});
    }

