package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.SearchingUser.SearchingUserDataItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchingAcount extends RecyclerView.Adapter<SearchingAcount.AdapterHolder> {
    private final OnItemClickListener listener;
    private final Context context;
    private final List<SearchingUserDataItem> dataList;
    private final ApiInterface apiInterface;
    SesionManager sesionManager;


    public SearchingAcount(Context context, List<SearchingUserDataItem> dataList, OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }


    @NonNull
    @Override
    public SearchingAcount.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_search_posting, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchingAcount.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final SearchingUserDataItem item = dataList.get(position);
        String name = item.getUsername();
        String url = "http://edifarm.yoganova.my.id/";
        String fotoProfil = String.valueOf(item.getPhoto());
        holder.namaAkun.setText(name);
        String imageUrl = url + fotoProfil;
        Picasso.get().load(imageUrl).into(holder.fotoProfil);


    }

    public int getItemCount() {
        return dataList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(SearchingAcount adapter, View view, int position, SearchingUserDataItem item);

        void onStatusClick(SearchingAcount adapter, View view, int position, SearchingUserDataItem item);
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        private final boolean mIsLiked = false;
        ImageButton fotoProfil;
        TextView namaAkun;


        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            fotoProfil = itemView.findViewById(R.id.imageProfilsearch);
            namaAkun = itemView.findViewById(R.id.textProfils);

        }
    }

}
