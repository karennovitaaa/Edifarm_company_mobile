package com.aditiyagilang.edifarm_company;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.dashboardfixx.dashboardfix;
import com.aditiyagilang.edifarm_company.firebase.MyFirebaseMessagingService;
import com.aditiyagilang.edifarm_company.model.ChangePassword;
import com.aditiyagilang.edifarm_company.model.OTP.OTP;
import com.aditiyagilang.edifarm_company.model.login.Login;
import com.aditiyagilang.edifarm_company.model.login.LoginData;
import com.aditiyagilang.edifarm_company.model.pass.Pas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity implements View.OnClickListener {

    EditText usernameField, passwordField;
    String Username, Password;
    TextView tv_daftar;
    ApiInterface apiInterface;
    SesionManager sesionManager;
    MyFirebaseMessagingService myFirebaseMessagingService;
    Button lupapass;

    Button btn_login;
    private String token;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myFirebaseMessagingService = new MyFirebaseMessagingService();
        // Mendapatkan referensi ke EditText untuk username dan password
        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        btn_login = findViewById(R.id.login);
        lupapass = findViewById(R.id.lupapas);
        tv_daftar = findViewById(R.id.daftar1);
        tv_daftar.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        lupapass.setOnClickListener(this);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FOM", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();


                        Log.d("FOM", token);
                        Toast.makeText(login.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                Username = usernameField.getText().toString();
                Password = passwordField.getText().toString();
                String Fcm_token = token;
                login(Username, Password, Fcm_token);
                break;

            case R.id.daftar1:
                Intent intent = new Intent(this, register.class);
                startActivity(intent);
                break;
            case R.id.lupapas:
                popemail();
        }
    }

    private void popemail() {
        final Dialog dialog = new Dialog(login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pop_email);
        Button sendemail = dialog.findViewById(R.id.sendemail);
        EditText mail = dialog.findViewById(R.id.esendemail);
        String emails = mail.getText().toString();

        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mail.getText().toString();

                apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<Pas> loginCall = apiInterface.checkEmailResponse(email);
                loginCall.enqueue(new Callback<Pas>() {
                    @Override
                    public void onResponse(Call<Pas> call, Response<Pas> response) {
                        if (response.body() != null && response.body().isSuccess() && response.isSuccessful()) {
                            showOTPDialog();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(login.this, emails, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Pas> call, Throwable t) {
                        Toast.makeText(login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("DATA", t.toString());
                    }
                });
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    private void showOTPDialog() {
        final Dialog dialog = new Dialog(login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pop_otp);
        Button sendotp = dialog.findViewById(R.id.sendotp);
        EditText otp = dialog.findViewById(R.id.eotp);
        String otps = otp.getText().toString();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otps = otp.getText().toString();
                apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<OTP> loginCall = apiInterface.checkOtpResponse(otps);
                loginCall.enqueue(new Callback<OTP>() {
                    @Override
                    public void onResponse(Call<OTP> call, Response<OTP> response) {
                        if (response.body() != null && response.body().isSuccess() && response.isSuccessful()) {
                            dialog.dismiss();
                            final Dialog dialog = new Dialog(login.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.pop_change_pass);
                            Button sendotp = dialog.findViewById(R.id.sendpass);
                            EditText changepass = dialog.findViewById(R.id.changepass);
                            EditText changepassconfirm = dialog.findViewById(R.id.changepassconfirm);

                            sendotp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String pass = changepass.getText().toString();
                                    String confpass = changepassconfirm.getText().toString();
                                    String otps = otp.getText().toString();

                                    apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                    Call<ChangePassword> loginCall = apiInterface.gantiPasswordResponse(otps, pass, confpass);
                                    loginCall.enqueue(new Callback<ChangePassword>() {
                                        @Override
                                        public void onResponse(Call<ChangePassword> call, Response<ChangePassword> response) {
                                            if (response.body() != null && response.body().isSuccess() && response.isSuccessful()) {
                                                dialog.dismiss();
                                                final Dialog dialog = new Dialog(login.this);
                                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                dialog.setContentView(R.layout.pop_up_done);
                                                Button done = dialog.findViewById(R.id.done);
                                                TextView massage = dialog.findViewById(R.id.massegedone);
                                                massage.setText(response.body().getMessage());
                                                done.setOnClickListener(new View.OnClickListener() {
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
                                            } else {
                                                Toast.makeText(login.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ChangePassword> call, Throwable t) {
                                            Toast.makeText(login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                            Log.d("DATA", t.toString());
                                        }
                                    });
                                }
                            });

                            dialog.show();
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                            dialog.getWindow().setGravity(Gravity.CENTER);
                        } else {
                            Toast.makeText(login.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OTP> call, Throwable t) {
                        Toast.makeText(login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("DATA", t.toString());
                    }
                });
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
        dialog.getWindow().setGravity(Gravity.CENTER);
    }


    private void login(String username, String password, String fcm_token) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginresponse(username, password, fcm_token);
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
                    final Dialog dialog = new Dialog(login.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.pop_tryagain);
                    Button oke = dialog.findViewById(R.id.dones);
                    TextView massage = dialog.findViewById(R.id.massegedone);

                    oke.setText("Login");
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
            public void onFailure(Call<Login> call, Throwable t) {
                final Dialog dialog = new Dialog(login.this);
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
        });

    }

}
