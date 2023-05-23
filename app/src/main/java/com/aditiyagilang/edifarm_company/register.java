package com.aditiyagilang.edifarm_company;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.register.Register;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity implements View.OnClickListener {
    ApiInterface apiInterface;
    String Username, Name, Address, Phone, Paswword, Born_Date, Email, CPassword;
    ImageButton baddress;
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
    private Button tv_login;
    private ProgressBar progres;
    private double latitudes; // Declare latitude as a field
    private double longitudes;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eusername = findViewById(R.id.username_field);
        ename = findViewById(R.id.name_field);
        ephone = findViewById(R.id.phone_field);
        epassword = findViewById(R.id.password_field);
        eaddress = findViewById(R.id.address);
        baddress = findViewById(R.id.baddress);
        eborn_date = findViewById(R.id.born_date_field);
        eemail = findViewById(R.id.email_field);
        daftar = findViewById(R.id.daftar);
        tv_login = findViewById(R.id.tv_login);


        ecpassword = findViewById(R.id.cpassword_field);
//        progres = (ProgressBar) findViewById(R.id.progres);

        tv_login.setOnClickListener(this);
        daftar.setOnClickListener(this);
        LocationManager locationManager;
        LocationListener locationListener;
        locationManager = (LocationManager) register.this.getSystemService(Context.LOCATION_SERVICE);
        final int REQUEST_LOCATION_PERMISSION = 1;

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Mendapatkan data lokasi latitude dan longitude
                // Mendapatkan data lokasi latitude dan longitude
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
// Mendapatkan nama lokasi berdasarkan latitude dan longitude menggunakan Reverse Geocoding
                Geocoder geocoder = new Geocoder(register.this, Locale.getDefault());
                try {
                    List<android.location.Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    if (addresses != null && addresses.size() > 0) {
                        Address address = addresses.get(0);
                        String locationName = address.getAddressLine(0);

                        // Menyimpan data lokasi dalam variabel
                        String locationString = "Location: " + locationName;

                        // Menampilkan data lokasi dalam EditText text_location
                        eaddress.setText(locationString);

                        // Menyimpan latitude dan longitude dalam variabel
                        String latitudeString = "Latitude: " + latitude;
                        String longitudeString = "Longitude: " + longitude;
                        register.this.latitudes = latitude;
                        register.this.longitudes = longitude;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };


        baddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Periksa izin lokasi
                if (ContextCompat.checkSelfPermission(register.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(register.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {
                    // Izin lokasi diberikan, dapatkan lokasi pengguna
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                } else {
                    // Izin lokasi tidak diberikan, minta izin
                    ActivityCompat.requestPermissions(register.this,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION},
                            REQUEST_LOCATION_PERMISSION);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.daftar:
                Username = eusername.getText().toString();
                Name = ename.getText().toString();
                Address = eaddress.getText().toString();
                Phone = ephone.getText().toString();
                Paswword = epassword.getText().toString();
                Born_Date = eborn_date.getText().toString();
                Email = eemail.getText().toString();
                CPassword = ecpassword.getText().toString();
                String Latitudes = String.valueOf(latitudes);
                String Longitude = String.valueOf(longitudes);

                register(Username, Name, Address, Phone, Paswword, Born_Date, Email, CPassword, Latitudes, Longitude);

            case R.id.tv_login:
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);

        }

    }

    private void register(String username, String name,
                          String address, String phone, String password,
                          String born_date, String email, String confirm_password, String latitude, String longitude) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.registerresponse(username, name,
                address, phone, password, born_date, email, confirm_password, latitude, longitude);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.isSuccessful() && response.body().isSuccess() && response != null) {
                    Toast.makeText(register.this, response.body().getMassage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(register.this, berhasil_login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(register.this, response.body().getMassage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });
    }

}
