package com.aditiyagilang.edifarm_company;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.util.HashMap;

public class edit_profile extends AppCompatActivity {
    EditText eusername, ename, eemail, eaddress, ephone, eborn_date;
    String username, name, address, phone, photo, email, born_date;
    SesionManager sesionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sesionManager = new SesionManager(edit_profile.this);
        username = sesionManager.getUserDetail().get(SesionManager.USERNAME);
        name = sesionManager.getUserDetail().get(SesionManager.NAME);
        address = sesionManager.getUserDetail().get(SesionManager.ADDRESS);
        phone = sesionManager.getUserDetail().get(SesionManager.PHONE);
        email = sesionManager.getUserDetail().get(SesionManager.EMAIL);
        born_date = sesionManager.getUserDetail().get(SesionManager.BORN_DATE);

        eusername = (EditText) findViewById(R.id.editusername);
        if (SesionManager.USERNAME == null) {
            eusername.setHint("Masukan Username");
        } else {
            eusername.setHint(username);
        }

        ename = (EditText) findViewById(R.id.editnama);
        if(SesionManager.NAME == null){
            ename.setHint("Masukan Nama");
        } else {
            ename.setHint(name);
        }

        eemail = (EditText) findViewById(R.id.editemail);
        if(SesionManager.EMAIL == null){
            eemail.setHint("Masukan Email");
        } else {
            eemail.setHint(email);
        }

        eaddress = (EditText) findViewById(R.id.editalamat);
        if(SesionManager.ADDRESS == null){
            eaddress.setHint("Masukan Alamat");
        } else {
            eaddress.setHint(address);
        }

        ephone = (EditText) findViewById(R.id.editnohp);
        if(SesionManager.PHONE == null){
            ephone.setHint("Masukan Nomer Handphone");
        } else {
            ephone.setHint(phone);
        }

        eborn_date = (EditText) findViewById(R.id.edittgllahir);
        if(SesionManager.BORN_DATE == null){
            eborn_date.setHint("Masukan Tanggal Lahir");
        } else {
            eborn_date.setHint(born_date);
        }
    }
}