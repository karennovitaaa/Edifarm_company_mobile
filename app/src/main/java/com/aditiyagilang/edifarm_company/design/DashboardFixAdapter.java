package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;
import com.aditiyagilang.edifarm_company.model.dashboard_model.DashboardDataItem;

import java.util.List;

public class DashboardFixAdapter extends RecyclerView.Adapter<DashboardFixAdapter.AdapterHolder> {

    private final DashboardFixAdapter.OnItemClickListener listener;
    private final Context context;
    private final List<DashboardDataItem> dataList;
    DashboardDataItem dashboardDataItem;
    SesionManager sesionManager;
    ApiInterface apiInterface;

    public DashboardFixAdapter(Context context, List<DashboardDataItem> dataList, DashboardFixAdapter.OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @NonNull
    @Override
    public DashboardFixAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.posts_list_item, parent, false);
        return new DashboardFixAdapter.AdapterHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DashboardFixAdapter.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {

        final String User_ID = sesionManager.getUserDetail().get(SesionManager.ID);
        final String Id = String.valueOf(dataList.get(position).getId());

        final DashboardDataItem item = dataList.get(position);
      String textProfil = String.valueOf(dataList.get(position).getUserId());
      String fotoProfil = String.valueOf(dataList.get(position).getImage());
      String Api = "https://3302-36-74-202-227.ngrok-free.ap";
      String tanggal = String.valueOf(dataList.get(position).getCreatedAt());
      String gambarpost = String.valueOf(dataList.get(position).getImage());
      String textCaption = String.valueOf(dataList.get(position).getCaption());


        holder.namaAkun.setText(textProfil);
        holder.tanggalPost.setText(tanggal);
        holder.caption.setText(textCaption);
        holder.fotoProfil.setImageResource(Integer.parseInt(Api+fotoProfil));
        holder.gambarPosting.setImageResource(Integer.parseInt(Api+gambarpost));
        holder.dashboardDataItem = item;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(DashboardFixAdapter.this, view, position, item);
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

        void onStatusClick(DashboardFixAdapter adapter, View view, int position, DashboardDataItem item);
    }


    public class AdapterHolder extends RecyclerView.ViewHolder {
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
        SesionManager sesionManager;
        ApiInterface apiInterface;
        DashboardDataItem dashboardDataItem;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            fotoProfil = itemView.findViewById(R.id.imageProfilPost);
            namaAkun = itemView.findViewById(R.id.textProfil);
            reportButton = itemView.findViewById(R.id.button_repost);
            gambarPosting = itemView.findViewById(R.id.listImagePost);
            tanggalPost = itemView.findViewById(R.id.text_tanggal_post);
            commentButton = itemView.findViewById(R.id.comment_button);
            likeButton = itemView.findViewById(R.id.like_button);
            jumlahLike = itemView.findViewById(R.id.jumlah_like);
            jumlahComment = itemView.findViewById(R.id.jumlahh_comment);
            caption = itemView.findViewById(R.id.text_caption);



            sesionManager = new SesionManager(itemView.getContext());
            apiInterface = ApiClient.getClient().create(ApiInterface.class);

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                    }
                }
            });
        }

    }


}





