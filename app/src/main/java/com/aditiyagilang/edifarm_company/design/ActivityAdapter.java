package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.UpActivity.UpActivity;
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.AdapterHolder> {

    private final OnItemClickListener listener;
    private final Context context;
    private final List<ActivityDataItem> dataList;
    ActivityDataItem activityDataItem;
    SesionManager sesionManager;
    ApiInterface apiInterface;

    public ActivityAdapter(Context context, List<ActivityDataItem> dataList, OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }


    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_activity, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, @SuppressLint("RecyclerView") int position) {

        final String User_ID = sesionManager.getUserDetail().get(SesionManager.ID);
        final String Id = String.valueOf(dataList.get(position).getId());

        final ActivityDataItem item = dataList.get(position);
        String activity_name = item.getActivityName();
        String status1 = item.getStatus();
        String plantname = item.getPlantName();


        holder.nama_kegiatan.setText(activity_name);
        holder.status.setText(status1);
        holder.planName.setText(plantname);
        holder.activityDataItem = item;

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
                Toast.makeText(context, Id + User_ID, Toast.LENGTH_SHORT).show();
                if (listener != null) {
                    listener.onStatusClick(ActivityAdapter.this, view, position, item);
                    klaim(Id);
                }
            }
        });
    }

    public void klaim(String id) {
        Call<UpActivity> UpActCall = apiInterface.UpactResponse(id);
        UpActCall.enqueue(new Callback<UpActivity>() {
            @Override
            public void onResponse(Call<UpActivity> call, Response<UpActivity> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    // update the activity status locally

                    notifyDataSetChanged();
                    Toast.makeText(context, response.body().getMessage() + "Mari Gess", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, response.body().getMessage() + "Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpActivity> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ActivityAdapter adapter, View view, int position, ActivityDataItem item);

        void onStatusClick(ActivityAdapter adapter, View view, int position, ActivityDataItem item);
    }


    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView nama_kegiatan, planName;
        Button status;

        SesionManager sesionManager;
        ApiInterface apiInterface;
        ActivityDataItem activityDataItem;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            nama_kegiatan = itemView.findViewById(R.id.nama_kegiatan);
            status = itemView.findViewById(R.id.status);
            sesionManager = new SesionManager(itemView.getContext());
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            planName = itemView.findViewById(R.id.textsession);

            status.setOnClickListener(new View.OnClickListener() {
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

