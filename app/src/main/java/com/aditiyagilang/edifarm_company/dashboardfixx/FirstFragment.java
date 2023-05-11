package com.aditiyagilang.edifarm_company.dashboardfixx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
<<<<<<< HEAD
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
=======

import com.aditiyagilang.edifarm_company.databinding.FragmentFirstBinding;
>>>>>>> 62bb525c16240a4b45c7c455c570de0fbd3c7304

import com.aditiyagilang.edifarm_company.Activity.First2Fragment;
import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;

import com.aditiyagilang.edifarm_company.databinding.FragmentDashboardfixBinding;
import com.aditiyagilang.edifarm_company.databinding.FragmentFirstBinding;
import com.aditiyagilang.edifarm_company.design.ActivityAdapter;
import com.aditiyagilang.edifarm_company.design.DashboardFixAdapter;
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;
import com.aditiyagilang.edifarm_company.model.dashboard_model.DashboardDataItem;
import com.aditiyagilang.edifarm_company.model.dashboard_model.DashboardModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment  implements View.OnClickListener, DashboardFixAdapter.OnItemClickListener {

private FragmentDashboardBinding binding;


    ImageView fotoProfil;
    TextView namaAkun;
    ImageButton reportButton;
    ImageView gambarPosting;
    TextView tanggalPost;
    ImageButton commentButton;
    ImageButton likeButton;
    TextView jumlahLike;
    TextView jumlahComment;
    TextView caption;
    RecyclerView recyclerView;
    SesionManager sesionManager;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    DashboardDataItem dashboardDataItem;

<<<<<<< HEAD
=======
    private FragmentFirstBinding binding;
>>>>>>> 62bb525c16240a4b45c7c455c570de0fbd3c7304

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

<<<<<<< HEAD
      binding = FragmentDashboardBinding.inflate(inflater, container, false);
      return binding.getRoot();
=======
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
>>>>>>> 62bb525c16240a4b45c7c455c570de0fbd3c7304

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

<<<<<<< HEAD
        fotoProfil = getView().findViewById(R.id.imageProfilPost);
        namaAkun = getView().findViewById(R.id.textProfil);
        reportButton = getView().findViewById(R.id.button_repost);
        gambarPosting = getView().findViewById(R.id.listImagePost);
        tanggalPost = getView().findViewById(R.id.text_tanggal_post);
        commentButton = getView().findViewById(R.id.comment_button);
        likeButton = getView().findViewById(R.id.like_button);
        jumlahLike = getView().findViewById(R.id.jumlah_like);
        jumlahComment = getView().findViewById(R.id.jumlahh_comment);
        caption = getView().findViewById(R.id.text_caption);
        sesionManager = new SesionManager(requireContext());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        recyclerView = getView().findViewById(R.id.postlist);


        Call<DashboardModel> dashCall = apiInterface.GetPostResponse();
        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                // Panggil kembali API setiap 5 detik
                Call<DashboardModel> dashCall = apiInterface.GetPostResponse();
                dashCall.enqueue(new Callback<DashboardModel>() {
                    @Override
                    public void onResponse(Call<DashboardModel> call, Response<DashboardModel> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                            List<DashboardDataItem> dashboardDataItemList = response.body().getData();
                            DashboardFixAdapter dashboardfixAdapter = new DashboardFixAdapter(getContext(), dashboardDataItemList, FirstFragment.this);

                            recyclerView.setAdapter(dashboardfixAdapter);
                            dashboardDataItem = dashboardDataItemList.get(0);
                        }
                    }

                    @Override
                    public void onFailure(Call<DashboardModel> call, Throwable t) {

                    }
                });


            }


        }, 0, 5000);
    }

    @Override
    public void onClick(View v) {

=======
//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
>>>>>>> 62bb525c16240a4b45c7c455c570de0fbd3c7304
    }

    @Override
    public void onItemClick(DashboardFixAdapter adapter, View view, int position, DashboardDataItem item) {

    }

    @Override
    public void onStatusClick(DashboardFixAdapter adapter, View view, int position, DashboardDataItem item) {

    }
}