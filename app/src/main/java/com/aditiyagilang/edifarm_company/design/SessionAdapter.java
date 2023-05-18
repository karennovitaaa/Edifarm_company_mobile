package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.aditiyagilang.edifarm_company.model.ClearSession.ClearSession;
import com.aditiyagilang.edifarm_company.model.UpdateSesion.UpdateSesion;
import com.aditiyagilang.edifarm_company.model.deleteSession.DeleteSession;
import com.aditiyagilang.edifarm_company.model.documentation.Documentation;
import com.aditiyagilang.edifarm_company.model.getSession.GetSessionDataItem;
import com.aditiyagilang.edifarm_company.session.First3Fragment;
import com.aditiyagilang.edifarm_company.session.Sesession_jenis;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.AdapterHolder> {
    private final OnItemClickListener listener;
    private final Context context;
    private final List<GetSessionDataItem> dataList;
    GetSessionDataItem getSessionDataItem;
    SesionManager sesionManager;
    ApiInterface apiInterface;

    public SessionAdapter(Context context, List<GetSessionDataItem> dataList, First3Fragment listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }


    @NonNull
    @Override
    public SessionAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_session, parent, false);
        return new SessionAdapter.AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionAdapter.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {

        final String User_ID = sesionManager.getUserDetail().get(SesionManager.ID);
        final String Id = String.valueOf(dataList.get(position).getId());

        final GetSessionDataItem item = dataList.get(position);
        String plant_name = item.getPlantName();
        String status1 = item.getStatus();
        String start = item.getStart();
        String finish = item.getEnd();


        holder.plant_name.setText(plant_name);
        holder.clear.setText(status1);
        holder.start.setText(start);
        holder.finish.setText(finish);
        holder.getSessionDataItem = item;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(SessionAdapter.this, view, position, item);
                }
            }
        });

        holder.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (listener != null) {
                    listener.onStatusClick(SessionAdapter.this, view, position, item);
                    klaim(Id, User_ID);
                }


            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onUpdateClick(SessionAdapter.this, view, position, item);

                }

                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fragment_edit_session);
                String[] items = {"Belum", "Selesai"};
                AutoCompleteTextView autoCompleteTextView;
                ArrayAdapter<String> adapterItems;

                EditText estart = dialog.findViewById(R.id.session_field_tanggals);
                EditText efinish = dialog.findViewById(R.id.session_field_tanggal_selesais);
                autoCompleteTextView = dialog.findViewById(R.id.pilih_status);
                adapterItems = new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_dropdown_item_1line, items);
                autoCompleteTextView.setAdapter(adapterItems);
                ImageButton startDate = dialog.findViewById(R.id.secalStarts);
                ImageButton endDate = dialog.findViewById(R.id.secalEnds);
                Button uploadsesedit = dialog.findViewById(R.id.upload_sessions);
                final String[] state = {""};
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        state[0] = items[position]; // Mendapatkan pilihan yang dipilih
                    }
                });


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


                uploadsesedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {

                            String id = Id;
                            String status = state[0];
                            if (TextUtils.isEmpty(status)) {
                                status = item.getStatus();
                            }

                            String start = estart.getText().toString();
                            if (TextUtils.isEmpty(start)) {
                                start = item.getStart();
                            }
                            String end = efinish.getText().toString();
                            if (TextUtils.isEmpty(end)) {
                                end = item.getEnd();
                            }
                            Toast.makeText(context, id + " " + start + "status" + status, Toast.LENGTH_SHORT).show();


                            listener.onUpdateClick(SessionAdapter.this, view, position, item);
                            // Call the API method with the appropriate parameters
                            edit(id, status, start, end);
                            dialog.dismiss();
                        }
                    }
                });


                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);


            }

            public void edit(String id, String status, String start, String end) {
                Call<UpdateSesion> UpActCall = apiInterface.UpdateSesResponse(id, status, start, end);
                UpActCall.enqueue(new Callback<UpdateSesion>() {
                    @Override
                    public void onResponse(Call<UpdateSesion> call, Response<UpdateSesion> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            // update the activity status locally
                            Intent intent = new Intent(context, Sesession_jenis.class);

                            notifyDataSetChanged();
                            Toast.makeText(context, response.body().getMessage() + "Mari Gess", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(context, response.body().getMessage() + "Salah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateSesion> call, Throwable t) {
                        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDeleteClick(SessionAdapter.this, view, position, item);
                    delete(User_ID, Id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void delete(String user_id, String id) {
        Call<DeleteSession> UpActCall = apiInterface.DeleteSessionResponse(user_id, id);
        UpActCall.enqueue(new Callback<DeleteSession>() {
            @Override
            public void onResponse(Call<DeleteSession> call, Response<DeleteSession> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    // update the activity status locally
                    Intent intent = new Intent(context, Sesession_jenis.class);

                    notifyDataSetChanged();
                    Toast.makeText(context, response.body().getMessage() + "Mari Gess", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(context, response.body().getMessage() + "Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteSession> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void klaim(String id, String user_id) {
        Call<ClearSession> UpActCall = apiInterface.updateStatusSessionResponse(id, user_id);
        UpActCall.enqueue(new Callback<ClearSession>() {
            @Override
            public void onResponse(Call<ClearSession> call, Response<ClearSession> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    // update the activity status locally

                    notifyDataSetChanged();
                    Toast.makeText(context, response.body().getMessage() + "Mari Gess", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Sesession_jenis.class);
                    Call<Documentation> UpActCall = apiInterface.generateReportResponse(user_id, id);
                    UpActCall.enqueue(new Callback<Documentation>() {
                        @Override
                        public void onResponse(Call<Documentation> call, Response<Documentation> response) {
                            if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                                // update the activity status locally

                                notifyDataSetChanged();
                                Toast.makeText(context, response.body().getMessage() + "Mari Gess", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, Sesession_jenis.class);

                            } else {
                                Toast.makeText(context, response.body().getMessage() + "Salah", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Documentation> call, Throwable t) {
                            Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(context, response.body().getMessage() + "Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClearSession> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(SessionAdapter adapter, View view, int position, GetSessionDataItem item);

        void onStatusClick(SessionAdapter adapter, View view, int position, GetSessionDataItem item);

        void onDeleteClick(SessionAdapter adapter, View view, int position, GetSessionDataItem item);

        void onUpdateClick(SessionAdapter adapter, View view, int position, GetSessionDataItem item);
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView plant_name, start, finish;

        Button clear;
        Button uploadsesedit;
        ImageButton edit, delete;
        SesionManager sesionManager;
        ApiInterface apiInterface;
        GetSessionDataItem getSessionDataItem;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            uploadsesedit = itemView.findViewById(R.id.upload_sessions);
            plant_name = itemView.findViewById(R.id.plant_name);
            start = itemView.findViewById(R.id.tanggal_start);
            finish = itemView.findViewById(R.id.tanggal_finish);
            clear = itemView.findViewById(R.id.clear);
            edit = itemView.findViewById(R.id.buttonedit);
            delete = itemView.findViewById(R.id.buttondelete);
            sesionManager = new SesionManager(itemView.getContext());
            apiInterface = ApiClient.getClient().create(ApiInterface.class);

            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                    }
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                    }
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
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