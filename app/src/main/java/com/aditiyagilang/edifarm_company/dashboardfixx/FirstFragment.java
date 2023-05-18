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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.FragmentPostingSosmedBinding;
import com.aditiyagilang.edifarm_company.design.DashboardFixAdapter;
import com.aditiyagilang.edifarm_company.model.dashboard_model.DashboardDataItem;
import com.aditiyagilang.edifarm_company.ui.DashboardModel;
import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment implements View.OnClickListener, DashboardFixAdapter.OnItemClickListener {

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
    ImageButton add_post;
    private FragmentPostingSosmedBinding binding;
    private LottieAnimationView progressBar;

// Dalam metode onViewCreated, dapatkan referensi ke LottieAnimationView dan atur animasi dan opsi lainnya

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPostingSosmedBinding.inflate(inflater, container, false);
        return binding.getRoot();

//        binding = FragmentFirstBinding.inflate(inflater, container, false);
//        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add_post = getView().findViewById(R.id.add_postings);
//        fotoProfil = getView().findViewById(R.id.imageProfilPost);
//        namaAkun = getView().findViewById(R.id.textProfil);
        reportButton = getView().findViewById(R.id.button_reason);
//        gambarPosting = getView().findViewById(R.id.listImagePost);
        tanggalPost = getView().findViewById(R.id.text_tanggal_post);
        commentButton = getView().findViewById(R.id.comment_button);
        likeButton = getView().findViewById(R.id.like_button);
        jumlahLike = getView().findViewById(R.id.jumlah_like);
        jumlahComment = getView().findViewById(R.id.jumlahh_comment);
        caption = getView().findViewById(R.id.text_caption);
        sesionManager = new SesionManager(requireContext());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        recyclerView = getView().findViewById(R.id.postlist);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        loadingIndicator = getView().findViewById(R.id.load_titiks);
        progressBar = getView().findViewById(R.id.load_titiku);
        progressBar.setAnimation(R.raw.load_titik);  // Ganti dengan file animasi Lottie Anda
        progressBar.setVisibility(View.VISIBLE);
        progressBar.playAnimation();


        binding.addPostings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_NewPost);
            }
        });
        // Panggil kembali API setiap 5 detik


        Call<DashboardModel> dashCall = apiInterface.GetPostResponse();
        dashCall.enqueue(new Callback<DashboardModel>() {
            @Override
            public void onResponse(Call<DashboardModel> call, Response<DashboardModel> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {


                    List<DashboardDataItem> dashboardDataItemList = response.body().getData();
                    DashboardFixAdapter dashboardfixAdapter = new DashboardFixAdapter(requireContext(), dashboardDataItemList, FirstFragment.this);


                    recyclerView.setAdapter(dashboardfixAdapter);
                    dashboardDataItem = dashboardDataItemList.get(0);

                    progressBar.setVisibility(View.GONE);
                    progressBar.cancelAnimation();
                }
            }

            @Override
            public void onFailure(Call<DashboardModel> call, Throwable t) {

            }
        });


    }


    @Override
    public void onClick(View v) {


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

    }


    @Override
    public void onItemClick(DashboardFixAdapter adapter, View view, int position, DashboardDataItem item) {

    }
}