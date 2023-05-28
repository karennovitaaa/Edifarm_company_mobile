package com.aditiyagilang.edifarm_company.Riwayat;

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
import com.aditiyagilang.edifarm_company.databinding.FragmentListHistoryBinding;
import com.aditiyagilang.edifarm_company.design.AdapterHistory;
import com.aditiyagilang.edifarm_company.model.History.History;
import com.aditiyagilang.edifarm_company.model.History.HistoryDataItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryList extends Fragment implements AdapterHistory.OnItemClickListener {
    SesionManager sesionManager;
    ApiInterface apiInterface;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    HistoryDataItem historyDataItem;
    private FragmentListHistoryBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentListHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sesionManager = new SesionManager(requireContext());
        recyclerView = getView().findViewById(R.id.list_history);
        linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<History> HisCall = apiInterface.getAllDocumentationsResponse(user_id);

        HisCall.enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {

                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                    List<HistoryDataItem> historyDataItemList = response.body().getData();
                    AdapterHistory adapterHistory = new AdapterHistory(getContext(), historyDataItemList, HistoryList.this);

                    recyclerView.setAdapter(adapterHistory);
                    historyDataItem = historyDataItemList.get(0);
                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
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
    public void onItemClick(AdapterHistory adapter, View view, int position, HistoryDataItem item) {

    }

    @Override
    public void onDownloadsClick(AdapterHistory adapter, View view, int position, HistoryDataItem item) {

    }
}