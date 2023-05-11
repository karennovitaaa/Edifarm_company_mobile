package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.dashboard_model.DashboardDataItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DashboardFixAdapter extends RecyclerView.Adapter<DashboardFixAdapter.AdapterHolder> {

    private final OnItemClickListener listener;
    private final Context context;
    private final List<DashboardDataItem> dataList;
    private final SesionManager sesionManager;
    private final ApiInterface apiInterface;

    public DashboardFixAdapter(Context context, List<DashboardDataItem> dataList, OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.posts_list_item, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final DashboardDataItem item = dataList.get(position);
        String textProfil = String.valueOf(item.getUserId());
        String fotoProfil = String.valueOf(item.getPhoto());
        String imageUrl = "https://98be-103-160-182-11.ngrok-free.app/" + fotoProfil;

        String tanggal = String.valueOf(item.getCreatedAt());
        String gambarpost = String.valueOf(item.getImage());
        String imageUrlP = "https://98be-103-160-182-11.ngrok-free.app/" + gambarpost;
        String textCaption = String.valueOf(item.getCaption());
        String akunname = item.getUsername();
        String tanggals = item.getCreatedAt();
        String cap = item.getCaption();
        Picasso.get().load(imageUrl).into(holder.fotoProfil);
        Picasso.get().load(imageUrlP).into(holder.gambarPosting);
        holder.namaAkun.setText(akunname);
        holder.tanggalPost.setText(tanggals);
        holder.caption.setText(item.getCaption());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(DashboardFixAdapter.this, view, position, item);
                }
            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            private boolean mIsLiked = false;

            @Override
            public void onClick(View view) {


                ImageButton likeButton = view.findViewById(R.id.like_button);
                if (!mIsLiked) {
                    likeButton.setBackgroundResource(R.drawable.like_red);
                    mIsLiked = true;
                    Toast.makeText(view.getContext(), "Liked", Toast.LENGTH_SHORT).show();
                } else {
                    likeButton.setBackgroundResource(R.drawable.like_white);
                    mIsLiked = false;
                    Toast.makeText(view.getContext(), "Unliked", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(DashboardFixAdapter adapter, View view, int position, DashboardDataItem item);
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        ImageView fotoProfil;
        TextView namaAkun;
        ImageView gambarPosting;
        TextView tanggalPost;
        TextView caption;
        ImageButton like;
        private final boolean mIsLiked = false;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            fotoProfil = itemView.findViewById(R.id.imageProfilPost);
            namaAkun = itemView.findViewById(R.id.textProfil);
            gambarPosting = itemView.findViewById(R.id.listImagePost);
            tanggalPost = itemView.findViewById(R.id.text_tanggal_post);
            caption = itemView.findViewById(R.id.text_caption);
            like = itemView.findViewById(R.id.like_button);
        }
    }
}
