package com.aditiyagilang.edifarm_company.Biographical;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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


        sesionManager = new SesionManager(getContext());
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Call<GetPostUser> dashCall = apiInterface.getPostUserResponse(user_id);
        dashCall.enqueue(new Callback<GetPostUser>() {
            @Override
            public void onResponse(Call<GetPostUser> call, Response<GetPostUser> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                    List<GetPostUserDataItem> postUserDataItemList = response.body().getData();
//                    AdapterPostUser adapterPostLike = new AdapterPostUser(requireContext(), postUserDataItemList, GetPostUserA.this);


                    if (!postUserDataItemList.isEmpty()) {
                        AdapterPostUser adapterPostLike = new AdapterPostUser(requireContext(), postUserDataItemList, GetPostUserA.this);
                        recyclerView.setAdapter(adapterPostLike);
                        dashboardDataItem = postUserDataItemList.get(0);
                    } else {
                        final Dialog dialog = new Dialog(getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.pop_tryagain);
                        Button oke = dialog.findViewById(R.id.dones);
                        TextView massage = dialog.findViewById(R.id.masseges);
                        massage.setText("Belum Ada Postingan");

                        oke.setText("Buat Postingan");

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

                } else {
                    final Dialog dialog = new Dialog(getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.pop_tryagain);
                    Button oke = dialog.findViewById(R.id.dones);
                    TextView massage = dialog.findViewById(R.id.masseges);
                    massage.setText("Buat Sesi Baru");

                    oke.setText("Oke");

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