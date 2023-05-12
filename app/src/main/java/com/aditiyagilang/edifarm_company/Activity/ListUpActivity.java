package com.aditiyagilang.edifarm_company.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.FragmentListUpActivityBinding;
import com.aditiyagilang.edifarm_company.design.ActivityListAdapter;
import com.aditiyagilang.edifarm_company.model.GetFullActivity.GetFullActivity;
import com.aditiyagilang.edifarm_company.model.GetFullActivity.GetFullActivityDataItem;
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListUpActivity extends Fragment implements View.OnClickListener, ActivityListAdapter.OnItemClickListener {

    public String Activity_Name, Status, Start, End, User_Id;
    SesionManager sesionManager;
    TextView activitys_name;
    ImageButton search;
    EditText esearch;
    ImageButton edit;
    ImageButton addAct;
    RecyclerView recyclerView;
    ActivityDataItem activityDataItem;
    GetFullActivityDataItem getFullActivityDataItem;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    private FragmentListUpActivityBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentListUpActivityBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        esearch = getView().findViewById(R.id.searchedit);
        activitys_name = getView().findViewById(R.id.nama_kegiatan);
        edit = getView().findViewById(R.id.buttonedit);
        sesionManager = new SesionManager(requireContext());
        search = getView().findViewById(R.id.search);
        recyclerView = getView().findViewById(R.id.listupact);
        linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String id = sesionManager.getUserDetail().get(SesionManager.ID);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<GetFullActivity> ActCall = apiInterface.actFullResponse(id);

        new Timer().scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // Panggil kembali API setiap 5 detik
                Call<GetFullActivity> ActCall = apiInterface.actFullResponse(id);

                ActCall.enqueue(new Callback<GetFullActivity>() {
                    @Override
                    public void onResponse(Call<GetFullActivity> call, Response<GetFullActivity> response) {

                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
                            List<GetFullActivityDataItem> getFullActivityDataItemList = response.body().getData();
                            ActivityListAdapter activitylistAdapter = new ActivityListAdapter(getContext(), getFullActivityDataItemList, ListUpActivity.this);

                            recyclerView.setAdapter(activitylistAdapter);
                            getFullActivityDataItem = getFullActivityDataItemList.get(0);
                        } else {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetFullActivity> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }, 0, 5000);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User_Id = sesionManager.getUserDetail().get(SesionManager.ID);
                String Search = esearch.getText().toString();
                Searching(User_Id, Search);

            }
        });
    }

    private void Searching(String user_id, String search) {
        Call<GetFullActivity> FillCall = apiInterface.filterActivityResponse(user_id, search);

        FillCall.enqueue(new Callback<GetFullActivity>() {
            @Override
            public void onResponse(Call<GetFullActivity> call, Response<GetFullActivity> response) {
                if (response.isSuccessful() && response.body().isSuccess() && response.body() != null) {
                    List<GetFullActivityDataItem> getFullActivityDataItemList = response.body().getData();
                    ActivityListAdapter activitylistAdapter = new ActivityListAdapter(getContext(), getFullActivityDataItemList, ListUpActivity.this);

                    recyclerView.setAdapter(activitylistAdapter);

                } else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetFullActivity> call, Throwable t) {

            }
        });

    }


    @Override
    public void onClick(View view) {

    }


    @Override
    public void onItemClick(ActivityListAdapter adapter, View view, int position, GetFullActivityDataItem item) {

    }

    @Override
    public void onDeleteClick(ActivityListAdapter adapter, View view, int position, GetFullActivityDataItem item) {

    }


}