package com.aditiyagilang.edifarm_company.dashboardfixx;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
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
    EditText filter;
    TextView caption;
    RecyclerView recyclerView;
    SesionManager sesionManager;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    DashboardDataItem dashboardDataItem;
    ImageButton add_post;
    SearchView search;
    ImageButton searching;
    private DashboardFixAdapter dashboardFixAdapter;
    private FragmentPostingSosmedBinding binding;
    private LottieAnimationView progressBar;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPostingSosmedBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add_post = getView().findViewById(R.id.add_postings);
        reportButton = getView().findViewById(R.id.button_reason);
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
        filter = getView().findViewById(R.id.action_search);


//        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String key) {
//                key = search.getQuery().toString();
//                // Panggil metode filter dengan kata kunci pencarian
//                dashboardFixAdapter.filter(key);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Tidak perlu melakukan apa-apa saat teks berubah
//                return false;
//            }
//        });


//        loadingIndicator = getView().findViewById(R.id.load_titiks);
        progressBar = getView().findViewById(R.id.load_titiku);
        progressBar.setAnimation(R.raw.load_titik);  // Ganti dengan file animasi Lottie Anda
        progressBar.setVisibility(View.VISIBLE);
        progressBar.playAnimation();

//        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dashboardFixAdapter.getfilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        list();
        binding.addPostings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_NewPost);
            }
        });

    }

    private void list() {
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        Call<DashboardModel> dashCall = apiInterface.GetPostResponse(user_id);
        dashCall.enqueue(new Callback<DashboardModel>() {
            @Override
            public void onResponse(Call<DashboardModel> call, Response<DashboardModel> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    List<DashboardDataItem> dashboardDataItemList = response.body().getData();
                    dashboardFixAdapter = new DashboardFixAdapter(requireContext(), dashboardDataItemList, FirstFragment.this);
                    recyclerView.setAdapter(dashboardFixAdapter);

                    // Setelah mengatur adapter, tambahkan pemanggilan metode getfilter()
                    dashboardFixAdapter.getfilter();

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