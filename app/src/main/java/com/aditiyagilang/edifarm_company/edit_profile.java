package com.aditiyagilang.edifarm_company;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class edit_profile extends AppCompatActivity {
    private TextView textViewPassword;
    private TextView textViewConfirmPassword;
    private Button buttonSimpan;
=======

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
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


>>>>>>> d114a539fbca5e3856680880d1f662265022fd33

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

<<<<<<< HEAD
        textViewPassword = (TextView) findViewById(R.id.field_pass);
        textViewPassword = (TextView) findViewById(R.id.field_confirmpass);
        buttonSimpan = (Button) findViewById(R.id.button_simpan);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    public void openDialog() {

=======
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
//                Id = "5";
//                Username = "aku";
//                Name = "aku";
//                Address = "jom";
//                Phone = "089";
//                Born_date = "2002-11-11";
//                Email = "akak@hmail.com";
//                Id = sesionManager.getUserDetail().get(SesionManager.ID);
                Username = sesionManager.getUserDetail().get(SesionManager.USERNAME);
                if (eusername.getText().toString() == null){
                    Username = sesionManager.getUserDetail().get(SesionManager.USERNAME);
                }
                Name = ename.getText().toString();
                if (ename.getText().toString() == null){
                    Name = sesionManager.getUserDetail().get(SesionManager.NAME);
                }
                Address = eaddress.getText().toString();
                if (eaddress.getText().toString() == null){
                    Address = sesionManager.getUserDetail().get(SesionManager.ADDRESS);
                }
                Phone = ephone.getText().toString();
                if (ephone.getText().toString()== null){
                    Phone = sesionManager.getUserDetail().get(SesionManager.PHONE);
                }
                Born_date = eborn_date.getText().toString();
                if (eborn_date.getText().toString() == null){
                    Born_date = sesionManager.getUserDetail().get(SesionManager.BORN_DATE);
                }
                Email = eemail.getText().toString();
                if (eemail.getText().toString() == null){
                    Email = sesionManager.getUserDetail().get(SesionManager.EMAIL);
                }
kirim(Username, Name, Address, Phone, Born_date, Email);

        }
>>>>>>> d114a539fbca5e3856680880d1f662265022fd33
    }

    private void kirim( String username, String name,
                       String address, String phone,
                       String born_date, String email){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Update> Ucall = apiInterface.updateResponse( username, name,
                address,  phone, born_date, email);

        Ucall.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {


                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Toast.makeText(edit_profile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("GETDATA" , response.body().getMessage());
                } else {
                    Toast.makeText(edit_profile.this, "Update failed: " + response.body().getData(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Update> call, Throwable throwable) {
                Toast.makeText(edit_profile.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }); {
    }


}}