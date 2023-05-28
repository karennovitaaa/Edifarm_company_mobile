package com.aditiyagilang.edifarm_company.dashboardfixx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.FragmentActivityPostBinding;
import com.aditiyagilang.edifarm_company.design.AdapterActivityPost;
import com.aditiyagilang.edifarm_company.model.ActivityPost.ActivityPost;
import com.aditiyagilang.edifarm_company.model.ActivityPost.ActivityPostDataItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPosts extends Fragment implements AdapterActivityPost.OnItemClickListener {
    SesionManager sesionManager;
    ApiInterface apiInterface;

    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    ActivityPostDataItem activityPostDataItem;
    private FragmentActivityPostBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentActivityPostBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sesionManager = new SesionManager(requireContext());
        recyclerView = getView().findViewById(R.id.activitypost);
        linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ActivityPost> HisCall = apiInterface.getPostActivityPostResponse();

        HisCall.enqueue(new Callback<ActivityPost>() {
            @Override
            public void onResponse(Call<ActivityPost> call, Response<ActivityPost> response) {

                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                    List<ActivityPostDataItem> activityPostList = response.body().getData();
                    AdapterActivityPost adapterHistory = new AdapterActivityPost(getContext(), activityPostList, ActivityPosts.this);

                    recyclerView.setAdapter(adapterHistory);
                    activityPostDataItem = activityPostList.get(0);
                }
            }

            @Override
            public void onFailure(Call<ActivityPost> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(AdapterActivityPost adapter, View view, int position, ActivityPostDataItem item) {

    }

    @Override
    public void onDownloadsClick(AdapterActivityPost adapter, View view, int position, ActivityPostDataItem item) {

    }
}