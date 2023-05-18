package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.GetComment.GetCommentDataItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterComen extends RecyclerView.Adapter<AdapterComen.AdapterHolder> {
    private final OnItemClickListener listener;
    private final Context context;
    private final List<GetCommentDataItem> dataList;
    private final SesionManager sesionManager;
    private final ApiInterface apiInterface;
    private List<GetCommentDataItem> commentDataList;

    public AdapterComen(Context context, List<GetCommentDataItem> dataList, OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_comment, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final GetCommentDataItem item = dataList.get(position);
        String url = "https://82fa-103-160-182-11.ngrok-free.app/";
        String textProfil = String.valueOf(item.getId());
        String fotoProfil = String.valueOf(item.getPhoto());
        String username = String.valueOf(item.getUsername());
        String tanggal = String.valueOf(item.getCreatedAt());
        String comment = String.valueOf(item.getComment());
        String imgUrl = url + fotoProfil;

        Picasso.get().load(imgUrl).into(holder.fotoProfil);
        holder.comment.setText(comment);
        holder.username.setText(username);
        holder.tanggal_coment.setText(tanggal);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(view, position, item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setCommentDataList(List<GetCommentDataItem> activityDataItemList) {
        this.commentDataList = activityDataItemList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, GetCommentDataItem item);
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        ImageView fotoProfil;
        TextView comment, tanggal_coment, username;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.isi_comment);
            tanggal_coment = itemView.findViewById(R.id.tanggal_comment);
            username = itemView.findViewById(R.id.username_comment);
            fotoProfil = itemView.findViewById(R.id.imagecomment);
        }
    }
}
