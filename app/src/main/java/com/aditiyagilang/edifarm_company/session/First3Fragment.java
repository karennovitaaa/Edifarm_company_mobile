package com.aditiyagilang.edifarm_company.session;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.FragmentListSessionBinding;
import com.aditiyagilang.edifarm_company.design.SessionAdapter;
import com.aditiyagilang.edifarm_company.model.addSession.AddSession;
import com.aditiyagilang.edifarm_company.model.getSession.GetSession;
import com.aditiyagilang.edifarm_company.model.getSession.GetSessionDataItem;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class First3Fragment extends Fragment implements View.OnClickListener, SessionAdapter.OnItemClickListener {
    ApiInterface apiInterface;
    SesionManager sesionManager;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    ImageButton edit, delete;
    Button clear;
    GetSessionDataItem getSessionDataItem;
    TextView plant_name, start, finish;
    private FragmentListSessionBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentListSessionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plant_name = getView().findViewById(R.id.plant_name);
        start = getView().findViewById(R.id.tanggal_start);
        finish = getView().findViewById(R.id.tanggal_finish);
        clear = getView().findViewById(R.id.clear);
        edit = getView().findViewById(R.id.buttonedit);
        sesionManager = new SesionManager(requireContext());
        delete = getView().findViewById(R.id.buttondelete);
        recyclerView = getView().findViewById(R.id.list_session);
        linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetSession> ActCall = apiInterface.GetSessionResponse(user_id);

        ActCall.enqueue(new Callback<GetSession>() {
            @Override
            public void onResponse(Call<GetSession> call, Response<GetSession> response) {
                Log.d("USER", user_id);
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Log.d("LALALU", response.body().getData().toString());
                    List<GetSessionDataItem> getSessionDataItemList = response.body().getData();
                    SessionAdapter sessionAdapter = new SessionAdapter(getContext(), getSessionDataItemList, First3Fragment.this);

                    recyclerView.setAdapter(sessionAdapter);


                    if (!getSessionDataItemList.isEmpty()) {
                        getSessionDataItem = getSessionDataItemList.get(0);
                    } else {
                        final Dialog dialog = new Dialog(getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.pop_tryagain);
                        Button oke = dialog.findViewById(R.id.dones);
                        TextView massage = dialog.findViewById(R.id.masseges);
                        massage.setText("Buat Sesi Baru");

                        oke.setText("Oke");
                        
                        oke.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                        dialog.getWindow().setGravity(Gravity.CENTER);
                    }
                } else {
                    final Dialog dialog = new Dialog(getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.pop_tryagain);
                    Button oke = dialog.findViewById(R.id.dones);
                    TextView massage = dialog.findViewById(R.id.massegedone);

                    oke.setText("Coba");
                    oke.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                    dialog.getWindow().setGravity(Gravity.CENTER);
                }
            }

            @Override
            public void onFailure(Call<GetSession> call, Throwable t) {
                t.printStackTrace();
            }
        });
        binding.addSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSession();
//                NavHostFragment.findNavController(First3Fragment.this)
//                        .navigate(R.id.action_First3Fragment_to_Second2Fragment);
            }
        });


    }

    private void addSession() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_second2);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        EditText estart = dialog.findViewById(R.id.session_field_tanggal);
        EditText efinish = dialog.findViewById(R.id.session_field_tanggal_selesai);
        EditText enameSession = dialog.findViewById(R.id.nameplant);

        ImageButton startDate = dialog.findViewById(R.id.secalStart);
        ImageButton endDate = dialog.findViewById(R.id.secalEnd);
        Button addSession = dialog.findViewById(R.id.upload_session);


        SesionManager sesionManager = new SesionManager(requireContext());
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
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


        addSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Plant_name = enameSession.getText().toString();
                String Start = estart.getText().toString();
                String End = efinish.getText().toString();
                String Id = sesionManager.getUserDetail().get(SesionManager.ID);
                Toast.makeText(getContext(), Plant_name + Start, Toast.LENGTH_SHORT).show();
                create(Plant_name, Start, End, Id);
                dialog.dismiss();

            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void create(String plant_name, String start, String end, String user_id) {
        Log.d("LLU", plant_name);
        Call<AddSession> ActCall = apiInterface.AddSessionResponse(plant_name, start, end, user_id);
        ActCall.enqueue(new Callback<AddSession>() {
            @Override
            public void onResponse(Call<AddSession> call, Response<AddSession> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    // pindah ke halaman first2_fragment dan merefresh halaman tersebut
                    Intent intent = new Intent(getContext(), Sesession_jenis.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddSession> call, Throwable t) {

                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(SessionAdapter adapter, View view, int position, GetSessionDataItem item) {

    }

    @Override
    public void onStatusClick(SessionAdapter adapter, View view, int position, GetSessionDataItem item) {

    }

    @Override
    public void onDeleteClick(SessionAdapter adapter, View view, int position, GetSessionDataItem item) {

    }

    @Override
    public void onUpdateClick(SessionAdapter adapter, View view, int position, GetSessionDataItem item) {

    }
}