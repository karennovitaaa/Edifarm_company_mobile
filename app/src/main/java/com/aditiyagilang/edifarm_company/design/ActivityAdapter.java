package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;

import java.util.List;
public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.AdapterHolder> {

    private OnItemClickListener listener;
    private Context context;
    private List<ActivityDataItem> dataList;

    public ActivityAdapter(Context context, List<ActivityDataItem> dataList, OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ActivityAdapter adapter, View view, int position, ActivityDataItem item);
        void onStatusClick(ActivityAdapter adapter, View view, int position, ActivityDataItem item);
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_activity, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final ActivityDataItem item = dataList.get(position);
        String activity_name = item.getActivityName();
        String status = item.getStatus();
        String id = String.valueOf(item.getId());

        holder.nama_kegiatan.setText(activity_name);
        holder.status.setText(status);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(ActivityAdapter.this, view, position, item);
                }
            }
        });

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onStatusClick(ActivityAdapter.this, view, position, item);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView nama_kegiatan;
        Button status;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            nama_kegiatan = itemView.findViewById(R.id.nama_kegiatan);
            status = itemView.findViewById(R.id.klaim_k);

            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onStatusClick(ActivityAdapter.this, view, position, dataList.get(position));
                    }
                }
            });
        }
    }
}
