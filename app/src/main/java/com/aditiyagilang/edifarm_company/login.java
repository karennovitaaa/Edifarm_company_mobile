package com.aditiyagilang.edifarm_company;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.dashboardfixx.dashboardfix;
import com.aditiyagilang.edifarm_company.model.login.Login;
import com.aditiyagilang.edifarm_company.model.login.LoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity implements View.OnClickListener {

    EditText usernameField, passwordField;
    String Username, Password;
    TextView tv_daftar;
    ApiInterface apiInterface;
    SesionManager sesionManager;

    Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Mendapatkan referensi ke EditText untuk username dan password
        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        btn_login = findViewById(R.id.login);
        tv_daftar = findViewById(R.id.daftar1);
        tv_daftar.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                Username = usernameField.getText().toString();
                Password = passwordField.getText().toString();
                login(Username, Password);
                break;

            case R.id.daftar1:
                Intent intent = new Intent(this, register.class);
                startActivity(intent);
                break;
        }
    }

    private void login(String username, String password) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginresponse(username, password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null && response.body().isSuccess() && response.isSuccessful()) {
                    sesionManager = new SesionManager(login.this);
                    LoginData loginData = response.body().getData();
                    sesionManager.createLoginSession(loginData);

                    Toast.makeText(login.this, response.body().getData().getUsername(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, dashboardfix.class);

                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(login.this, response.body().getMassage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("DATA", t.toString());
            }
        });

    }
}
