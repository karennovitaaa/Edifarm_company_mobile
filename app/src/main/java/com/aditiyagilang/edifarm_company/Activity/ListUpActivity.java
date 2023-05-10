package com.aditiyagilang.edifarm_company.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.FragmentListUpActivityBinding;
import com.aditiyagilang.edifarm_company.design.ActivityListAdapter;
import com.aditiyagilang.edifarm_company.model.activity.Activity;
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
    ImageButton edit;
    ImageButton addAct;
    RecyclerView recyclerView;
    ActivityDataItem activityDataItem;
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

        activitys_name = getView().findViewById(R.id.nama_kegiatan);
        edit = getView().findViewById(R.id.buttonedit);
        sesionManager = new SesionManager(requireContext());

        recyclerView = getView().findViewById(R.id.listupact);
        linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String id = sesionManager.getUserDetail().get(SesionManager.ID);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<Activity> ActCall = apiInterface.actResponse(id);

        new Timer().scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // Panggil kembali API setiap 5 detik
                Call<Activity> ActCall = apiInterface.actResponse(id);

                ActCall.enqueue(new Callback<Activity>() {
                    @Override
                    public void onResponse(Call<Activity> call, Response<Activity> response) {

                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                            List<ActivityDataItem> activityDataItemList = response.body().getData();
                            ActivityListAdapter activitylistAdapter = new ActivityListAdapter(getContext(), activityDataItemList, ListUpActivity.this);

                            recyclerView.setAdapter(activitylistAdapter);
                            activityDataItem = activityDataItemList.get(0);
                        }
                    }

                    @Override
                    public void onFailure(Call<Activity> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }, 0, 5000);


    }


    @Override
    public void onClick(View view) {

    }


    @Override
    public void onItemClick(ActivityListAdapter adapter, View view, int position, ActivityDataItem item) {

    }

    @Override
    public void onStatusClick(ActivityListAdapter adapter, View view, int position, ActivityDataItem item) {

    }
}