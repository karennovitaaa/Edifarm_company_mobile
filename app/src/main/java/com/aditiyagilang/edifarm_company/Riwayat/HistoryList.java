package com.aditiyagilang.edifarm_company.Riwayat;

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
import android.widget.TextView;

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

                    if (response.body().getData() != null) {
                        historyDataItem = historyDataItemList.get(0);
                    } else {
                        final Dialog dialog = new Dialog(getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.pop_tryagain);
                        Button oke = dialog.findViewById(R.id.dones);
                        TextView massage = dialog.findViewById(R.id.massegedone);

                        oke.setText("Coba");
                        oke.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                        dialog.getWindow().setGravity(Gravity.CENTER);
                    }

                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                t.printStackTrace();
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.pop_tryagain);
                Button oke = dialog.findViewById(R.id.dones);
                TextView massage = dialog.findViewById(R.id.massegedone);

                oke.setText("Coba");
                oke.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                dialog.getWindow().setGravity(Gravity.CENTER);
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