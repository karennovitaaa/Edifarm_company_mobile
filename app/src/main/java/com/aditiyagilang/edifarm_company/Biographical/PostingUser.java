package com.aditiyagilang.edifarm_company.Biographical;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
import com.aditiyagilang.edifarm_company.dashboardfixx.dashboardfix;
import com.aditiyagilang.edifarm_company.databinding.FragmentLikeUserBinding;
import com.aditiyagilang.edifarm_company.design.AdapterPostLike;
import com.aditiyagilang.edifarm_company.model.GetPostLike.GetPostLike;
import com.aditiyagilang.edifarm_company.model.GetPostLike.GetPostLikeDataItem;
import com.airbnb.lottie.LottieAnimationView;

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
    GetPostLikeDataItem dgetPostDataItem;
    private FragmentLikeUserBinding binding;
    private LottieAnimationView progressBar;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLikeUserBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sesionManager = new SesionManager(requireContext());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        recyclerView = getView().findViewById(R.id.listu);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        Call<GetPostLike> dashCall = apiInterface.getPostlikeResponse(user_id);
        progressBar = getView().findViewById(R.id.load_titiku);
        progressBar.setAnimation(R.raw.load_titik);  // Ganti dengan file animasi Lottie Anda
        progressBar.setVisibility(View.VISIBLE);
        progressBar.playAnimation();
        dashCall.enqueue(new Callback<GetPostLike>() {
            @Override
            public void onResponse(Call<GetPostLike> call, Response<GetPostLike> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                    List<GetPostLikeDataItem> postLikeDataItemList = response.body().getData();
//                    AdapterPostLike adapterPostLike = new AdapterPostLike(requireContext(), postLikeDataItemList, PostingUser.this);


//                    recyclerView.setAdapter(adapterPostLike);
                    if (!postLikeDataItemList.isEmpty()) {
                        AdapterPostLike adapterPostLike = new AdapterPostLike(requireContext(), postLikeDataItemList, PostingUser.this);
                        recyclerView.setAdapter(adapterPostLike);
                        dgetPostDataItem = postLikeDataItemList.get(0);
                        progressBar.setVisibility(View.GONE);
                        progressBar.cancelAnimation();
                    } else {
                        final Dialog dialog = new Dialog(getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.pop_tryagain);
                        Button oke = dialog.findViewById(R.id.dones);
                        TextView massage = dialog.findViewById(R.id.masseges);
                        massage.setText("Belum Menyukai Postingan Apapun");

                        oke.setText("Oke");


                        oke.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                Intent intent = new Intent(getContext(), dashboardfix.class);
                                startActivity(intent);
                            }
                        });
                        dialog.show();
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                        dialog.getWindow().setGravity(Gravity.CENTER);
                    }
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetPostLike> call, Throwable t) {

            }
        });


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
    public void onItemClick(AdapterPostLike adapter, View view, int position, GetPostLikeDataItem item) {

    }
}