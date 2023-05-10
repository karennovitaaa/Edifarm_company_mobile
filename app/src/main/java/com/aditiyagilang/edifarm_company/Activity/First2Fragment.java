package com.aditiyagilang.edifarm_company.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.aditiyagilang.edifarm_company.design.ActivityAdapter.OnItemClickListener;
import com.aditiyagilang.edifarm_company.model.activity.Activity;
import com.aditiyagilang.edifarm_company.model.activity.ActivityDataItem;
import com.aditiyagilang.edifarm_company.model.addActivity.AddActivity;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class First2Fragment extends Fragment implements View.OnClickListener, OnItemClickListener {
    public String Activity_Name, Status, Start, End, User_Id;
    SesionManager sesionManager;
    TextView activitys_name;
    Button status;
    ImageButton addAct;
    RecyclerView recyclerView;
    ActivityDataItem activityDataItem;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    private FragmentFirst2Binding binding;

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
        String id = sesionManager.getUserDetail().get(SesionManager.ID);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<Activity> ActCall = apiInterface.actResponse(id);

        new Timer().scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // Panggil kembali API setiap 5 detik
                Call<Activity> ActCall = apiInterface.actResponse(id);

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
        }, 0, 5000);


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
                navController.navigate(R.id.action_First2Fragment_to_ListActivity);
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

