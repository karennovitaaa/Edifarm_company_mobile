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

public class AdapterPostLike extends RecyclerView.Adapter<AdapterPostLike.AdapterHolder> {
    private final OnItemClickListener listener;
    private final Context context;
    private final List<DashboardDataItem> dataList;
    private final SesionManager sesionManager;
    private final ApiInterface apiInterface;

    public AdapterPostLike(Context context, List<DashboardDataItem> dataList, AdapterPostLike.OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @NonNull
    @Override
    public AdapterPostLike.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_posting_user, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPostLike.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final DashboardDataItem item = dataList.get(position);
        String textProfil = String.valueOf(item.getUserId());
        String fotoProfil = String.valueOf(item.getPhoto());
        String imageUrl = "https://3eca-103-160-182-11.ngrok-free.app/" + fotoProfil;

        String tanggal = String.valueOf(item.getCreatedAt());
        String gambarpost = String.valueOf(item.getImage());
        String imageUrlP = "https://3eca-103-160-182-11.ngrok-free.app/" + gambarpost;
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
                    listener.onItemClick(AdapterPostLike.this, view, position, item);
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
        void onItemClick(AdapterPostLike adapter, View view, int position, DashboardDataItem item);
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        private final boolean mIsLiked = false;
        ImageView fotoProfil;
        TextView namaAkun;
        ImageView gambarPosting;
        TextView tanggalPost;
        TextView caption;
        ImageButton like;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            fotoProfil = itemView.findViewById(R.id.imageProfilPostu);
            namaAkun = itemView.findViewById(R.id.textProfilUsers);
            gambarPosting = itemView.findViewById(R.id.listImagePostUser);
            tanggalPost = itemView.findViewById(R.id.text_tanggal_postu);
            caption = itemView.findViewById(R.id.text_captionu);
            like = itemView.findViewById(R.id.like_buttonu);
        }
    }
}
