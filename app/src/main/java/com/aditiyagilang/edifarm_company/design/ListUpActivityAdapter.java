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
import com.aditiyagilang.edifarm_company.model.FilterActivity.FilterActivity;
import com.aditiyagilang.edifarm_company.model.FilterActivity.FilterActivityDataItem;
import com.aditiyagilang.edifarm_company.model.GetFullActivity.GetFullActivity;
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUpActivityAdapter extends RecyclerView.Adapter<ListUpActivityAdapter.AdapterHolder>  {


    private final ListUpActivityAdapter.OnItemClickListener listener;
    private final Context context;
    private final List<FilterActivityDataItem> dataList;
    ActivityDataItem activityDataItem;
    SesionManager sesionManager;
    ApiInterface apiInterface;

    public ListUpActivityAdapter(Context context, List<FilterActivityDataItem> dataList, ListUpActivityAdapter.OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @NonNull
    @Override
    public ListUpActivityAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_activity, parent, false);
        return new ListUpActivityAdapter.AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUpActivityAdapter.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {

        final String User_ID = sesionManager.getUserDetail().get(SesionManager.ID);
        final String Id = String.valueOf(dataList.get(position).getId());

        final FilterActivityDataItem item = dataList.get(position);
        String activity_name = item.getActivityName();
        String status1 = item.getStatus();


        holder.nama_kegiatan.setText(activity_name);
        holder.status.setText(status1);
        holder.filterActivityDataItem = item;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(ListUpActivityAdapter.this, view, position, item);
                }
            }
        });

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, Id + User_ID, Toast.LENGTH_SHORT).show();
                if (listener != null) {
                    listener.onStatusClick(ListUpActivityAdapter.this, view, position, item);
                    klaim(Id, User_ID);
                }
            }
        });
    }

    public void klaim(String id, String user_id) {
        Call<GetFullActivity> UpActCall = apiInterface.filterActivityResponse(id, user_id);
        UpActCall.enqueue(new Callback<com.aditiyagilang.edifarm_company.model.GetFullActivity.GetFullActivity>() {
            @Override
            public void onResponse(Call<GetFullActivity> call, Response<GetFullActivity> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    // update the activity status locally

                    notifyDataSetChanged();
                    Toast.makeText(context, response.body().getMessage() + "Mari Gess", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, response.body().getMessage() + "Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetFullActivity> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ListUpActivityAdapter adapter, View view, int position, FilterActivityDataItem item);

        void onStatusClick(ListUpActivityAdapter adapter, View view, int position, FilterActivityDataItem item);
    }


    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView nama_kegiatan;
        Button status;

        SesionManager sesionManager;
        ApiInterface apiInterface;
        FilterActivityDataItem filterActivityDataItem;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            nama_kegiatan = itemView.findViewById(R.id.nama_kegiatan);
            status = itemView.findViewById(R.id.status);
            sesionManager = new SesionManager(itemView.getContext());
            apiInterface = ApiClient.getClient().create(ApiInterface.class);

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
