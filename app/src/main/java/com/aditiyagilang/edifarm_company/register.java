package com.aditiyagilang.edifarm_company;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.register.Register;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity implements View.OnClickListener {
    private EditText eusername;
    private EditText ename;
    private EditText eaddress;
    private EditText ephone;
    private EditText epassword;
    private EditText eborn_date;
    private EditText eemail;
    private EditText ecpassword;
    private Button daftar;
    private CheckBox acc;
    private TextView tv_login;
    ApiInterface apiInterface;
    String Username, Name, Address, Phone, Paswword, Born_Date, Email, CPassword;
    private ProgressBar progres;
    private String URL_REGIS = "http://127.0.0.1:8000/api/register";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eusername = (EditText) findViewById(R.id.username_field);
        ename = (EditText) findViewById(R.id.name_field);
        eaddress = (EditText) findViewById(R.id.address_field);
        ephone = (EditText) findViewById(R.id.phone_field);
        epassword = (EditText) findViewById(R.id.password_field);
        eborn_date = (EditText) findViewById(R.id.bord_date_field);
        eemail = (EditText) findViewById(R.id.email_field);
        daftar = (Button) findViewById(R.id.daftar);
        tv_login = (TextView) findViewById(R.id.tv_login);
        acc = (CheckBox) findViewById(R.id.setuju);
        ecpassword = (EditText) findViewById(R.id.cpassword_field);
        progres = (ProgressBar) findViewById(R.id.progres);

        tv_login.setOnClickListener(this);
        daftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.daftar:
            Username = eusername.getText().toString();
            Name = ename.getText().toString();
            Address = eaddress.getText().toString();
            Phone = ephone.getText().toString();
            Paswword = epassword.getText().toString();
            Born_Date = eborn_date.getText().toString();
            Email = eemail.getText().toString();
            CPassword = ecpassword.getText().toString();
            register(Username, Name, Address, Phone, Paswword, Born_Date, Email, CPassword);
       }

    }
    private void register(String username, String name,
                          String address, String phone, String password,
                          String born_date, String email, String confirm_password){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.registerresponse(username, name,
               address, phone, password, born_date, email, confirm_password );
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.isSuccessful() && response.body().isSuccess()){
                    Toast.makeText(register.this, response.body().getMassage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });
    }

    }
