//package com.aditiyagilang.edifarm_company.design;
//
//import android.annotation.SuppressLint;
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.text.TextUtils;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.aditiyagilang.edifarm_company.Activity.ListUpActivity;
//import com.aditiyagilang.edifarm_company.R;
//import com.aditiyagilang.edifarm_company.SesionManager;
//import com.aditiyagilang.edifarm_company.api.ApiClient;
//import com.aditiyagilang.edifarm_company.api.ApiInterface;
//import com.aditiyagilang.edifarm_company.model.FilterActivity.FilterActivityDataItem;
//import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;
//import com.aditiyagilang.edifarm_company.model.deleteActivity.DeleteActivity;
//import com.aditiyagilang.edifarm_company.model.updateactivity.UpdateActivitys;
//
//import java.util.Calendar;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class ListUpActivityAdapter extends RecyclerView.Adapter<ListUpActivityAdapter.AdapterHolder> {
//
//
//    private final ListUpActivityAdapter.OnItemClickListener listener;
//    private final Context context;
//    private final List<FilterActivityDataItem> dataList;
//    ActivityDataItem activityDataItem;
//    SesionManager sesionManager;
//    ApiInterface apiInterface;
//
//    public ListUpActivityAdapter(Context context, List<FilterActivityDataItem> dataList, ListUpActivity listener) {
//        this.context = context;
//        this.dataList = dataList;
//        this.listener = listener;
//        sesionManager = new SesionManager(context);
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//    }
//
//    @NonNull
//    @Override
//    public ListUpActivityAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.card_activity, parent, false);
//        return new ListUpActivityAdapter.AdapterHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ListUpActivityAdapter.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
//
//        final String User_ID = sesionManager.getUserDetail().get(SesionManager.ID);
//        final String Id = String.valueOf(dataList.get(position).getId());
//
//        final FilterActivityDataItem item = dataList.get(position);
//        String activity_name = item.getActivityName();
//        String status1 = item.getStatus();
//
//
//        holder.nama_kegiatans.setText(activity_name);
//
//        holder.filterActivityDataItem = item;
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (listener != null) {
//                    listener.onItemClick(ListUpActivityAdapter.this, view, position, item);
//                }
//            }
//        });
//
//        holder.buttondelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (listener != null) {
//                    listener.onDeleteClick(ListUpActivityAdapter.this, view, position, item);
//                    delete(Id, User_ID);
//                }
//            }
//
//            private void delete(String id, String user_id) {
//                Call<DeleteActivity> UpActCall = apiInterface.deleteactResponse(id, user_id);
//                UpActCall.enqueue(new Callback<DeleteActivity>() {
//                    @Override
//                    public void onResponse(Call<DeleteActivity> call, Response<DeleteActivity> response) {
//                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
//                            // Perbarui data pada objek ActivityDataItem
//                            dataList.remove(position);
//                            notifyItemRemoved(position);
//                            notifyItemRangeChanged(position, dataList.size());
//
//                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, response.body().getMessage() + " Salah", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<DeleteActivity> call, Throwable t) {
//                        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//        holder.buttonedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final Dialog dialog = new Dialog(view.getContext());
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.fragment_update_activity);
//
//
//                EditText estart = dialog.findViewById(R.id.upfield_tanggal);
//                EditText efinish = dialog.findViewById(R.id.upfield_tanggal_selesai);
//                EditText enameActivity = dialog.findViewById(R.id.upnameAct);
//
//                ImageButton startDate = dialog.findViewById(R.id.upcalStart);
//                ImageButton endDate = dialog.findViewById(R.id.upcalEnd);
//                Button upActivity = dialog.findViewById(R.id.edit);
//
//
//                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//                SesionManager sesionManager = new SesionManager(dialog.getContext());
//                estart.setHint("yy-mm-dd");
//                efinish.setHint("yy-mm-dd");
//                startDate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // Mendapatkan tanggal saat ini
//                        final Calendar calendar = Calendar.getInstance();
//                        int year = calendar.get(Calendar.YEAR);
//                        int month = calendar.get(Calendar.MONTH);
//                        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//
//                        // Membuat date picker dialog
//                        DatePickerDialog datePickerDialog = new DatePickerDialog(dialog.getContext(),
//                                new DatePickerDialog.OnDateSetListener() {
//                                    @Override
//                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                        // Mengubah tanggal yang dipilih menjadi string dengan format yyyy-mm-dd
//                                        String dayOfMonthString = String.format("%02d", dayOfMonth);
//                                        String monthString = String.format("%02d", month + 1);
//                                        String yearString = String.format("%04d", year);
//                                        String selectedDate = yearString + "-" + monthString + "-" + dayOfMonthString;
//
//                                        // Menampilkan tanggal yang dipilih di EditText
//                                        estart.setText(selectedDate);
//                                    }
//                                }, year, month, dayOfMonth);
//
//                        // Menampilkan date picker dialog
//                        datePickerDialog.show();
//                    }
//                });
//
//                endDate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        // Mendapatkan tanggal saat ini
//                        final Calendar calendar = Calendar.getInstance();
//                        int year = calendar.get(Calendar.YEAR);
//                        int month = calendar.get(Calendar.MONTH);
//                        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//
//                        // Membuat date picker dialog
//                        DatePickerDialog datePickerDialog = new DatePickerDialog(dialog.getContext(),
//                                new DatePickerDialog.OnDateSetListener() {
//                                    @Override
//                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                        // Mengubah tanggal yang dipilih menjadi string dengan format yyyy-mm-dd
//                                        String dayOfMonthString = String.format("%02d", dayOfMonth);
//                                        String monthString = String.format("%02d", month + 1);
//                                        String yearString = String.format("%04d", year);
//                                        String selectedDate = yearString + "-" + monthString + "-" + dayOfMonthString;
//
//                                        // Menampilkan tanggal yang dipilih di EditText
//                                        efinish.setText(selectedDate);
//                                    }
//                                }, year, month, dayOfMonth);
//
//                        // Menampilkan date picker dialog
//                        datePickerDialog.show();
//                    }
//                });
//
//
//                upActivity.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String activityName = enameActivity.getText().toString();
//                        if (TextUtils.isEmpty(activityName)) {
//                            activityName = item.getActivityName();
//                        }
//                        String status = "belum";
//                        String start = estart.getText().toString();
//                        if (TextUtils.isEmpty(start)) {
//                            start = item.getStart();
//                        }
//                        String end = efinish.getText().toString();
//                        if (TextUtils.isEmpty(end)) {
//                            end = item.getEnd();
//                        }
//                        String userId = User_ID;
//                        String id = String.valueOf(item.getId());
//
//                        Toast.makeText(context, activityName, Toast.LENGTH_SHORT).show();
//
//                        // Call the API method with the appropriate parameters
//                        klaim(id, userId, activityName, status, start, end);
//                        dialog.dismiss();
//                    }
//                });
//
//
//                dialog.show();
//                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//                dialog.getWindow().setGravity(Gravity.BOTTOM);
//
//
//            }
//
//
//        });
//    }
//
//    public void klaim(String id, String user_id, String activity_name, String status, String start, String end) {
//        if (sesionManager != null) {
//            // Gunakan user_id yang diteruskan sebagai parameter
//            Call<UpdateActivitys> UpActCall = apiInterface.UpdateActResponse(id, user_id, start, end, status, activity_name);
//            UpActCall.enqueue(new Callback<UpdateActivitys>() {
//                @Override
//                public void onResponse(Call<UpdateActivitys> call, Response<UpdateActivitys> response) {
//                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
//                        // Perbarui data pada objek ActivityDataItem
//
//                        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
//
//                        Toast.makeText(context, response.body().getMessage() + id, Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "Salah", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<UpdateActivitys> call, Throwable t) {
//                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(ListUpActivityAdapter adapter, View view, int position, FilterActivityDataItem item);
//
//        void onDeleteClick(ListUpActivityAdapter adapter, View view, int position, FilterActivityDataItem item);
//    }
//
//
//    public class AdapterHolder extends RecyclerView.ViewHolder {
//        public FilterActivityDataItem filterActivityDataItem;
//        TextView nama_kegiatans;
//        ImageButton buttonedit;
//        ImageButton buttondelete;
//
//        SesionManager sesionManager;
//        ApiInterface apiInterface;
//
//
//        public AdapterHolder(@NonNull View itemView) {
//            super(itemView);
//            nama_kegiatans = itemView.findViewById(R.id.nama_kegiatans);
//            buttondelete = itemView.findViewById(R.id.buttondelete);
//            buttonedit = itemView.findViewById(R.id.buttonedit);
//            sesionManager = new SesionManager(itemView.getContext());
//            apiInterface = ApiClient.getClient().create(ApiInterface.class);
//
//
////            status.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////                    int position = getAdapterPosition();
////                    if (position != RecyclerView.NO_POSITION) {
////                    }
////                }
////            });
//        }
//    }
//
//}
