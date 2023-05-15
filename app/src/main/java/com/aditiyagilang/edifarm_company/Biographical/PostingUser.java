package com.aditiyagilang.edifarm_company.Biographical;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.FragmentPostingUserBinding;
import com.aditiyagilang.edifarm_company.design.AdapterPostLike;
import com.aditiyagilang.edifarm_company.model.dashboard_model.DashboardDataItem;
import com.aditiyagilang.edifarm_company.model.dashboard_model.DashboardModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostingUser extends Fragment implements View.OnClickListener, AdapterPostLike.OnItemClickListener {

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
    private FragmentPostingUserBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPostingUserBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fotoProfil = getView().findViewById(R.id.imageProfilPostu);
        namaAkun = getView().findViewById(R.id.textProfilUsers);
        reportButton = getView().findViewById(R.id.button_repostu);
        gambarPosting = getView().findViewById(R.id.listImagePostUser);
        tanggalPost = getView().findViewById(R.id.text_tanggal_postu);
        commentButton = getView().findViewById(R.id.comment_buttonu);
        likeButton = getView().findViewById(R.id.like_buttonu);
        jumlahLike = getView().findViewById(R.id.jumlah_likeu);
        jumlahComment = getView().findViewById(R.id.jumlahh_commentu);
        caption = getView().findViewById(R.id.text_captionu);
        sesionManager = new SesionManager(requireContext());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        recyclerView = getView().findViewById(R.id.postuser);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Call<DashboardModel> dashCall = apiInterface.GetPostResponse();
        dashCall.enqueue(new Callback<DashboardModel>() {
            @Override
            public void onResponse(Call<DashboardModel> call, Response<DashboardModel> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                    List<DashboardDataItem> dashboardDataItemList = response.body().getData();
                    AdapterPostLike adapterPostLike = new AdapterPostLike(requireContext(), dashboardDataItemList, PostingUser.this);


                    recyclerView.setAdapter(adapterPostLike);
                    dashboardDataItem = dashboardDataItemList.get(0);
                    Toast.makeText(getContext(), response.body().getMassage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DashboardModel> call, Throwable t) {

            }
        });
//        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(PostingUser.this)
//                        .navigate(com.aditiyagilang.edifarm_company.R.id.action_Second3Fragment_to_First3Fragment);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterPostLike adapter, View view, int position, DashboardDataItem item) {

    }
}