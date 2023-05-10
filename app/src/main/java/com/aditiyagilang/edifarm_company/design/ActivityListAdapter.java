package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;
import com.aditiyagilang.edifarm_company.model.addActivity.AddActivity;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.AdapterHolder> {
    private final OnItemClickListener listener;
    private final Context context;
    private final List<ActivityDataItem> dataList;
    ActivityDataItem activityDataItem;
    SesionManager sesionManager;
    ApiInterface apiInterface;

    public ActivityListAdapter(Context context, List<ActivityDataItem> dataList, ActivityListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_edit_activity, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final String User_ID = sesionManager.getUserDetail().get(SesionManager.ID);
        final String Id = String.valueOf(dataList.get(position).getId());

        final ActivityDataItem item = dataList.get(position);
        String activity_name = item.getActivityName();
        String status1 = item.getStatus();


        holder.nama_kegiatans.setText(activity_name);
//        holder.status.setText(status1);
        holder.activityDataItem = item;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(ActivityListAdapter.this, view, position, item);
                }
            }
        });


        holder.buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fragment_update_activity);


                EditText estart = dialog.findViewById(R.id.upfield_tanggal);
                EditText efinish = dialog.findViewById(R.id.upfield_tanggal_selesai);
                EditText enameActivity = dialog.findViewById(R.id.upnameAct);

                ImageButton startDate = dialog.findViewById(R.id.upcalStart);
                ImageButton endDate = dialog.findViewById(R.id.upcalEnd);
                Button upActivity = dialog.findViewById(R.id.edit);

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                SesionManager sesionManager = new SesionManager(dialog.getContext());
                estart.setHint("yy-mm-dd");
                efinish.setHint("yy-mm-dd");
                startDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Mendapatkan tanggal saat ini
                        final Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                        // Membuat date picker dialog
                        DatePickerDialog datePickerDialog = new DatePickerDialog(dialog.getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        // Mengubah tanggal yang dipilih menjadi string dengan format yyyy-mm-dd
                                        String dayOfMonthString = String.format("%02d", dayOfMonth);
                                        String monthString = String.format("%02d", month + 1);
                                        String yearString = String.format("%04d", year);
                                        String selectedDate = yearString + "-" + monthString + "-" + dayOfMonthString;

                                        // Menampilkan tanggal yang dipilih di EditText
                                        estart.setText(selectedDate);
                                    }
                                }, year, month, dayOfMonth);

                        // Menampilkan date picker dialog
                        datePickerDialog.show();
                    }
                });

                endDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Mendapatkan tanggal saat ini
                        final Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                        // Membuat date picker dialog
                        DatePickerDialog datePickerDialog = new DatePickerDialog(dialog.getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        // Mengubah tanggal yang dipilih menjadi string dengan format yyyy-mm-dd
                                        String dayOfMonthString = String.format("%02d", dayOfMonth);
                                        String monthString = String.format("%02d", month + 1);
                                        String yearString = String.format("%04d", year);
                                        String selectedDate = yearString + "-" + monthString + "-" + dayOfMonthString;

                                        // Menampilkan tanggal yang dipilih di EditText
                                        efinish.setText(selectedDate);
                                    }
                                }, year, month, dayOfMonth);

                        // Menampilkan date picker dialog
                        datePickerDialog.show();
                    }
                });


                upActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String activityName = enameActivity.getText().toString();
                        String status = "belum";
                        String start = estart.getText().toString();
                        String end = efinish.getText().toString();
                        String userId = sesionManager.getUserDetail().get(SesionManager.ID);
                        create(activityName, status, start, end, userId);
                        dialog.dismiss();
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);

            }

            private void create(String Activity_Name, String Status, String Start, String End, String User_Id) {
                apiInterface.CreatActResponse(Activity_Name, Status, Start, End, User_Id).enqueue(new Callback<AddActivity>() {
                    @Override
                    public void onResponse(Call<AddActivity> call, Response<AddActivity> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(context.getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            // pindah ke halaman first2_fragment dan merefresh halaman tersebut

                        }
                    }

                    @Override
                    public void onFailure(Call<AddActivity> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ActivityListAdapter adapter, View view, int position, ActivityDataItem item);

        void onStatusClick(ActivityListAdapter adapter, View view, int position, ActivityDataItem item);
    }


    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView nama_kegiatans;
        ImageButton buttonedit;
        ImageButton buttondelete;

        SesionManager sesionManager;
        ApiInterface apiInterface;
        ActivityDataItem activityDataItem;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            nama_kegiatans = itemView.findViewById(R.id.nama_kegiatans);
            buttondelete = itemView.findViewById(R.id.buttondelete);
            buttonedit = itemView.findViewById(R.id.buttonedit);
            sesionManager = new SesionManager(itemView.getContext());
            apiInterface = ApiClient.getClient().create(ApiInterface.class);

//            status.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                    }
//                }
//            });
        }
    }
}
