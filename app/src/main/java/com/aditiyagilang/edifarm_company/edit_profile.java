package com.aditiyagilang.edifarm_company;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.UpdateBio.UpdateBio;
import com.aditiyagilang.edifarm_company.model.update.Update;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_profile extends AppCompatActivity implements View.OnClickListener {
    private EditText eusername;
    private EditText ename;
    private EditText eaddress;
    private EditText ephone;

    private EditText eborn_date;
    private EditText eemail;

    String Id, Username, Name, Address, Phone,  Email, Born_date;
    Button updated;
    SesionManager sesionManager;
    ApiInterface apiInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        sesionManager = new SesionManager(edit_profile.this);

        updated= (Button) findViewById(R.id.update);
        updated.setOnClickListener(this);

        Log.d("ID_USER", sesionManager.getUserDetail().get(SesionManager.ID));

        eusername = (EditText) findViewById(R.id.editusername);
        if (SesionManager.USERNAME == null) {
            eusername.setHint("Masukan Username");
        } else {
            eusername.setHint(sesionManager.getUserDetail().get(SesionManager.ID));
        }


        ename = (EditText) findViewById(R.id.editnama);
        if(SesionManager.NAME == null){
            ename.setHint("Masukan Nama");
        } else {
            ename.setHint(sesionManager.getUserDetail().get(SesionManager.NAME));
        }

        eemail = (EditText) findViewById(R.id.editemail);
        if(SesionManager.EMAIL == null){
            eemail.setHint("Masukan Email");
        } else {
            eemail.setHint(sesionManager.getUserDetail().get(SesionManager.EMAIL));
        }

        eaddress = (EditText) findViewById(R.id.editalamat);
        if(SesionManager.ADDRESS == null){
            eaddress.setHint("Masukan Alamat");
        } else {
            eaddress.setHint(sesionManager.getUserDetail().get(SesionManager.ADDRESS));
        }

        ephone = (EditText) findViewById(R.id.editnohp);
        if(SesionManager.PHONE == null){
            ephone.setHint("Masukan Nomer Handphone");
        } else {
            ephone.setHint(sesionManager.getUserDetail().get(SesionManager.PHONE));
        }

        eborn_date = (EditText) findViewById(R.id.edittgllahir);
        if(SesionManager.BORN_DATE == null){
            eborn_date.setHint("Masukan Tanggal Lahir");
        } else {
            eborn_date.setHint(sesionManager.getUserDetail().get(SesionManager.BORN_DATE));
        }



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.update:

                Id = sesionManager.getUserDetail().get(SesionManager.ID);
                Username = eusername.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.USERNAME) : eusername.getText().toString();
                Name = ename.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.NAME) : ename.getText().toString();
                Address = eaddress.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.ADDRESS) : eaddress.getText().toString();
                Phone = ephone.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.PHONE) : ephone.getText().toString();
                Born_date = eborn_date.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.BORN_DATE) : eborn_date.getText().toString();
                Email = eemail.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.EMAIL) : eemail.getText().toString();
                kirim(Id, Username, Name, Address, Phone, Born_date, Email);
                break;


        }
    }

    private void kirim( String id, String username, String name,
                        String address, String phone,
                        String born_date, String email){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UpdateBio> Ucall = apiInterface.updateResponse( id, username, name,
                address,  phone, born_date, email);

        Ucall.enqueue(new Callback<UpdateBio>() {
            @Override
            public void onResponse(Call<UpdateBio> call, Response<UpdateBio> response) {


                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Toast.makeText(edit_profile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("GETDATA" , response.body().getMessage());
                } else {
                    Toast.makeText(edit_profile.this, "Update failed: " + response.body().getData(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UpdateBio> call, Throwable throwable) {
                Toast.makeText(edit_profile.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("SERVER", throwable.toString());
            }
        }); {
        }



    }}