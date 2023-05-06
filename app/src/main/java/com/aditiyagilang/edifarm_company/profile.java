package com.aditiyagilang.edifarm_company;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.activity.Coba;
import com.aditiyagilang.edifarm_company.model.activity.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile extends AppCompatActivity implements View.OnClickListener {
EditText editText;
String Username, Id;
Button kirim;
ApiInterface apiInterface;
SesionManager sesionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        editText = findViewById(R.id.editTextc);
        kirim = findViewById(R.id.kirim);
        kirim.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.kirim:
                Id = sesionManager.getUserDetail().get(SesionManager.ID);
                Username = editText.getText().toString();
//                kirim(Id, Username);
                break;
        }
    }

//  break  private void kirim(String username, String id) {
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<Coba> Uc = apiInterface.updateResponse(username, id);
//        Uc.enqueue(new Callback<Coba>(){
//
//            @Override
//            public void onResponse(Call<Coba> call, Response<Coba> response) {
//                if(response.body() != null && response.body().isSuccess() && response.isSuccessful()){
//                    Data data = response.body().getData();
//                    Toast.makeText(profile.this, response.body().getData().getId(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Coba> call, Throwable t) {
//                Toast.makeText(profile.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
}