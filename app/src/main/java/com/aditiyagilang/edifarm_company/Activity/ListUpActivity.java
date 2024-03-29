package com.aditiyagilang.edifarm_company.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.aditiyagilang.edifarm_company.model.FilterActivity.FilterActivity;
import com.aditiyagilang.edifarm_company.model.FilterActivity.FilterActivityDataItem;
import com.aditiyagilang.edifarm_company.model.GetFullActivity.GetFullActivity;
import com.aditiyagilang.edifarm_company.model.GetFullActivity.GetFullActivityDataItem;
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUpActivity extends Fragment implements View.OnClickListener, ActivityListAdapter.OnItemClickListener {

    public String Activity_Name, Status, Start, End, User_Id;
    SesionManager sesionManager;
    TextView activitys_name;
    ImageButton search;
    EditText esearch;
    Button pindah;
    ImageButton edit;
    ImageButton addAct;
    RecyclerView recyclerView;
    ActivityDataItem activityDataItem;
    GetFullActivityDataItem getFullActivityDataItem;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    private ActivityListAdapter activityListAdapter;
    private List<GetFullActivityDataItem> originalDataList;
    private FragmentListUpActivityBinding binding;

    private List<GetFullActivityDataItem> currentDataList;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentListUpActivityBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        esearch = getView().findViewById(R.id.searchedit);
//        activitys_name = getView().findViewById(R.id.nama_kegiatan);
//        edit = getView().findViewById(R.id.buttonedit);
//        sesionManager = new SesionManager(requireContext());
//        search = getView().findViewById(R.id.search);
//        recyclerView = getView().findViewById(R.id.listupact);
//        linearLayoutManager = new LinearLayoutManager(requireContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        String User_id = sesionManager.getUserDetail().get(SesionManager.ID);
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        // Inisialisasi currentDataList dengan data asli
//
//
//        // Panggil kembali API setiap 5 detik
//
//
//        esearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                activityListAdapter.getfilters().filter(charSequence);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        list(User_id);
//
//    }

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
        String User_id = sesionManager.getUserDetail().get(SesionManager.ID);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        // Inisialisasi currentDataList dengan data asli


        // Panggil kembali API setiap 5 detik


        esearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (activityListAdapter != null) {
                    activityListAdapter.getfilters().filter(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        list(User_id);


    }

    private void list(String user_id) {

        Call<GetFullActivity> ActCall = apiInterface.actFullResponse(user_id);

        ActCall.enqueue(new Callback<GetFullActivity>() {
            @Override
            public void onResponse(Call<GetFullActivity> call, Response<GetFullActivity> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    List<GetFullActivityDataItem> getFullActivityDataItemList = response.body().getData();
                    activityListAdapter = new ActivityListAdapter(getContext(), getFullActivityDataItemList, ListUpActivity.this);
                    recyclerView.setAdapter(activityListAdapter);
                    activityListAdapter.getfilters();

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

    private void Searching(String user_id, String search) {

        Call<FilterActivity> FillCall = apiInterface.filterActivityResponse(user_id, search);

        FillCall.enqueue(new Callback<FilterActivity>() {
            @Override
            public void onResponse(Call<FilterActivity> call, Response<FilterActivity> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FilterActivity filterActivity = response.body();

                    if (filterActivity.isSuccess()) {
                        List<FilterActivityDataItem> getFilterActivityDataItemList = filterActivity.getData();

                        if (getFilterActivityDataItemList.isEmpty()) {
                            // Tampilkan pesan jika tidak ada hasil pencarian
                            Toast.makeText(getContext(), "No matching results found.", Toast.LENGTH_SHORT).show();
                        } else {
                            // Convert FilterActivityDataItem to GetFullActivityDataItem
                            List<GetFullActivityDataItem> convertedList = convertToGetFullActivityDataItemList(getFilterActivityDataItemList);

                            ActivityListAdapter activitylistAdapter = new ActivityListAdapter(getContext(), convertedList, ListUpActivity.this);
                            recyclerView.setAdapter(activitylistAdapter);
                            activitylistAdapter.setOnItemClickListener(ListUpActivity.this);
                            currentDataList.clear();
                            currentDataList.addAll(convertedList);

                        }
                    } else {
                        Toast.makeText(getContext(), filterActivity.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to get search results.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FilterActivity> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get search results.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private List<GetFullActivityDataItem> convertToGetFullActivityDataItemList(List<FilterActivityDataItem> filterActivityDataItemList) {
        List<GetFullActivityDataItem> convertedList = new ArrayList<>();

        for (FilterActivityDataItem item : filterActivityDataItemList) {
            GetFullActivityDataItem convertedItem = new GetFullActivityDataItem();
            convertedItem.setId(item.getId()); // Salin id dari FilterActivityDataItem ke GetFullActivityDataItem
            convertedItem.setUserId(item.getUserId()); // Salin user_id dari FilterActivityDataItem ke GetFullActivityDataItem
            convertedItem.setActivityName(item.getActivityName());
            convertedItem.setStatus(item.getStatus());
            convertedItem.setStart(item.getStart());
            convertedItem.setEnd(item.getEnd());
            // Set atribut lainnya sesuai kebutuhan

            convertedList.add(convertedItem);
        }

        return convertedList;
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(ActivityListAdapter adapter, View view, GetFullActivityDataItem item) {

    }

    @Override
    public void onDeleteClick(ActivityListAdapter adapter, View view, GetFullActivityDataItem item) {

    }

    @Override
    public void onDeleteClick(ActivityListAdapter adapter, View view, int position, GetFullActivityDataItem item) {

    }

    @Override
    public void onEditClick(ActivityListAdapter adapter, View view, GetFullActivityDataItem item) {

    }

    @Override
    public void onItemClick(ActivityListAdapter activityListAdapter, View view, int position, GetFullActivityDataItem item) {

    }

    @Override
    public void onEditClick(ActivityListAdapter activityListAdapter, View view, int position, GetFullActivityDataItem item) {

    }


}