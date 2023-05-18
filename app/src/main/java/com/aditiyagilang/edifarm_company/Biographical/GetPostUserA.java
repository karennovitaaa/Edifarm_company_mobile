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
import com.aditiyagilang.edifarm_company.design.AdapterPostUser;
import com.aditiyagilang.edifarm_company.model.GetPostUser.GetPostUser;
import com.aditiyagilang.edifarm_company.model.GetPostUser.GetPostUserDataItem;
import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPostUserA extends Fragment implements View.OnClickListener, AdapterPostUser.OnItemClickListener {

    ImageView fotoProfil;
    TextView namaAkun;
    ImageButton reportButton;
    ImageView gambarPosting;
    TextView tanggalPost;
    SesionManager sesionManager;

    ImageButton commentButton;
    ImageButton likeButton;
    TextView jumlahLike;
    TextView jumlahComment;
    TextView caption;
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    GetPostUserDataItem dashboardDataItem;
    private LottieAnimationView progressBar;
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
        namaAkun = getView().findViewById(R.id.textProfilUserss);
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
        progressBar = getView().findViewById(R.id.load_titiks);
        progressBar.setAnimation(R.raw.load_titik);  // Ganti dengan file animasi Lottie Anda
        progressBar.setVisibility(View.VISIBLE);
        progressBar.playAnimation();

        sesionManager = new SesionManager(getContext());
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Call<GetPostUser> dashCall = apiInterface.getPostUserResponse(user_id);
        dashCall.enqueue(new Callback<GetPostUser>() {
            @Override
            public void onResponse(Call<GetPostUser> call, Response<GetPostUser> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                    List<GetPostUserDataItem> postUserDataItemList = response.body().getData();
                    AdapterPostUser adapterPostLike = new AdapterPostUser(requireContext(), postUserDataItemList, GetPostUserA.this);


                    recyclerView.setAdapter(adapterPostLike);
                    dashboardDataItem = postUserDataItemList.get(0);
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    progressBar.cancelAnimation();
                }
            }

            @Override
            public void onFailure(Call<GetPostUser> call, Throwable t) {

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
    public void onItemClick(AdapterPostUser adapter, View view, int position, GetPostUserDataItem item) {

    }
}