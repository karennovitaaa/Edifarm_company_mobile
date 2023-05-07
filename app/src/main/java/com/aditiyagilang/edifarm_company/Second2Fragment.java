package com.aditiyagilang.edifarm_company;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
<<<<<<< HEAD
=======
import android.widget.ImageButton;
import android.widget.Toast;
>>>>>>> 461c83915aad293c8e3f682c2de6904defcc15e5

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.FragmentSecond2Binding;
import com.aditiyagilang.edifarm_company.model.activity.Activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Second2Fragment extends Fragment implements View.OnClickListener {
private Button addActivity;
    ApiInterface apiInterface;
    SesionManager sesionManager;
    Button addAct;
    private FragmentSecond2Binding binding;
    Button buttonsheet;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecond2Binding.inflate(inflater, container, false);
        return binding.getRoot();

    }

<<<<<<< HEAD



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
=======
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
addActivity = getView().findViewById(R.id.button_second);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        addActivity.setOnClickListener(this);
    }
>>>>>>> 461c83915aad293c8e3f682c2de6904defcc15e5

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
String Activity_Name,  Status, Start, End, User_Id;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_second:
            Activity_Name = "Nyalon Lurah";
            Status = "belum";
            Start = "2002-11-11";
            End = "2003-11-11";
            User_Id = "5";
            Creat(Activity_Name, Status, Start, End, User_Id);
        }
    }

    private void Creat(
            String activity_Name, String status, String start, String user_Id, String end
    ) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Activity> addCall = apiInterface.CreatActResponse(activity_Name, status, start, end, user_Id);
addCall.enqueue(new Callback<Activity>() {
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
}