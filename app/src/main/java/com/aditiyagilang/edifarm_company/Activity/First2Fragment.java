package com.aditiyagilang.edifarm_company.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.FragmentFirst2Binding;
import com.aditiyagilang.edifarm_company.design.ActivityAdapter;
import com.aditiyagilang.edifarm_company.model.activity.Activity;
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;
import com.aditiyagilang.edifarm_company.model.addActivity.AddActivity;
import com.aditiyagilang.edifarm_company.model.getSession.GetSession;
import com.aditiyagilang.edifarm_company.model.getSession.GetSessionDataItem;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class First2Fragment extends Fragment implements View.OnClickListener, ActivityAdapter.OnItemClickListener {
    public String Activity_Name, Status, Start, End, User_Id;
    SesionManager sesionManager;
    TextView activitys_name;
    TextView tanggal, jam;
    Button status;
    ImageButton addAct;
    RecyclerView recyclerView;
    ActivityDataItem activityDataItem;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    private FragmentFirst2Binding binding;
    private String selectedSessionId;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirst2Binding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activitys_name = getView().findViewById(R.id.nama_kegiatan);
        status = getView().findViewById(R.id.status);
        sesionManager = new SesionManager(requireContext());
        addAct = binding.addAct;
        recyclerView = getView().findViewById(R.id.tvlist_activity);
        linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        tanggal = getView().findViewById(R.id.tanggal);
        jam = getView().findViewById(R.id.jam);
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DateFormatSymbols symbols = new DateFormatSymbols(new Locale("id", "ID"));
        String monthName = symbols.getMonths()[month];
        String currentDate = dayOfMonth + " " + monthName + " " + year;
        tanggal.setText(currentDate);

        Handler handler;
        Runnable runnable;
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                // Dapatkan waktu saat ini
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", new Locale("id", "ID"));
                String currentTime = dateFormat.format(calendar.getTime());

                // Atur teks pada TextView jam
                jam.setText(currentTime + " WIB");

                // Jalankan pembaruan setiap detik
                handler.postDelayed(this, 1000);
            }
        };

// Mulai pembaruan setiap detik saat activity dimulai
        handler.postDelayed(runnable, 0);
        Call<Activity> ActCall = apiInterface.actResponse(user_id);

        new Timer().scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // Panggil kembali API setiap 5 detik
                Call<Activity> ActCall = apiInterface.actResponse(user_id);

                ActCall.enqueue(new Callback<Activity>() {
                    @Override
                    public void onResponse(Call<Activity> call, Response<Activity> response) {

                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                            List<ActivityDataItem> activityDataItemList = response.body().getData();
                            ActivityAdapter activityAdapter = new ActivityAdapter(getContext(), activityDataItemList, First2Fragment.this);

                            recyclerView.setAdapter(activityAdapter);
                            activityDataItem = activityDataItemList.get(0);
                        }
                    }

                    @Override
                    public void onFailure(Call<Activity> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }, 0, 5000000);


        addAct.setOnClickListener(this);


    }

//    String Id;
//    String Id_Kegiatan, Status;
//

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addAct:
                showDialog();
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_pop_button);
        ImageButton info = dialog.findViewById(R.id.button_inpo);
        ImageButton add = dialog.findViewById(R.id.button_add);
        ImageButton edit = dialog.findViewById(R.id.button_edtit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = NavHostFragment.findNavController(First2Fragment.this);
                navController.navigate(R.id.action_First2Fragment_to_List);
                dialog.dismiss();

            }
        });
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                addAct();
                dialog.dismiss();
            }
        });


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void addAct() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_secound_fragmen);
        AutoCompleteTextView listsession = dialog.findViewById(R.id.list_session);

        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        Call<GetSession> ActCall = apiInterface.getsesResponse(user_id);

        ActCall.enqueue(new Callback<GetSession>() {
            @Override
            public void onResponse(Call<GetSession> call, Response<GetSession> response) {
                Log.d("USER", user_id);
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Log.d("LALALU", response.body().getData().toString());
                    List<GetSessionDataItem> getSessionDataItemList = response.body().getData();

                    if (!getSessionDataItemList.isEmpty()) {
                        GetSessionDataItem getSessionDataItem = getSessionDataItemList.get(0);

                        // Menggunakan getSessionDataItemList sebagai data untuk dropdown
                        String[] items = new String[getSessionDataItemList.size()];
                        final String[] ids = new String[getSessionDataItemList.size()]; // Array untuk menyimpan ID
                        for (int i = 0; i < getSessionDataItemList.size(); i++) {
                            GetSessionDataItem item = getSessionDataItemList.get(i);
                            items[i] = item.getPlantName();
                            ids[i] = item.getId(); // Menyimpan ID pada array ids
                        }

                        // Set adapter dan item click listener untuk dropdown
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, items);
                        listsession.setAdapter(adapter);
                        listsession.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                selectedSessionId = ids[position]; // Mendapatkan ID item yang dipilih
                                // Gunakan selectedSessionId sesuai kebutuhan
                            }
                        });

                    } else {
                        Toast.makeText(requireContext(), "Kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Cok", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<GetSession> call, Throwable t) {
                t.printStackTrace();
            }
        });
        EditText estart = dialog.findViewById(R.id.field_tanggal);
        EditText efinish = dialog.findViewById(R.id.field_tanggal_selesai);
        EditText enameActivity = dialog.findViewById(R.id.nameAct);

        ImageButton startDate = dialog.findViewById(R.id.calStart);
        ImageButton endDate = dialog.findViewById(R.id.calEnd);
        Button addActivity = dialog.findViewById(R.id.upload);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        SesionManager sesionManager = new SesionManager(requireContext());
        estart.setHint("yy-mm-dd");
        efinish.setHint("yy-mm-dd");

        listsession.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String item = (String) adapterView.getItemAtPosition(position);
                // Gunakan item yang dipilih sesuai kebutuhan
            }
        });


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


        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String activityName = enameActivity.getText().toString();
                String status = "belum";
                String start = estart.getText().toString();
                String end = efinish.getText().toString();
                String session_id = selectedSessionId;
                create(activityName, status, start, end, session_id);
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void create(String Activity_Name, String Status, String Start, String End, String Session_Id) {
        apiInterface.CreatActResponse(Activity_Name, Status, Start, End, Session_Id).enqueue(new Callback<AddActivity>() {
            @Override
            public void onResponse(Call<AddActivity> call, Response<AddActivity> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    // pindah ke halaman first2_fragment dan merefresh halaman tersebut

                }
            }

            @Override
            public void onFailure(Call<AddActivity> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void onItemClick(ActivityDataItem activityDataItem) {
        this.activityDataItem = activityDataItem;
    }


    @Override
    public void onItemClick(ActivityAdapter adapter, View view, int position, ActivityDataItem item) {

    }

    @Override
    public void onStatusClick(ActivityAdapter adapter, View view, int position, ActivityDataItem item) {

    }

    public void addActivity() {

    }
}

