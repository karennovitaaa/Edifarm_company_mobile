package com.aditiyagilang.edifarm_company;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.UpdateBio.UpdateBio;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_profile extends AppCompatActivity implements View.OnClickListener {
    private static final int CAMERA_REQUEST_CODE = 3;
    private static final int GALLERY_REQUEST_CODE = 4;
    String Id, Username, Name, Address, Phone, Email, Born_date;
    ImageView ephoto;
    Button updated;
    SesionManager sesionManager;
    ApiInterface apiInterface;
    ImageButton poto;
    private EditText eusername;
    private EditText ename;
    private EditText eaddress;
    private EditText ephone;
    private EditText eborn_date;
    private EditText eemail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        sesionManager = new SesionManager(edit_profile.this);
        poto = findViewById(R.id.editpoto);
        ephoto = findViewById(R.id.photobio);
        String po = sesionManager.getUserDetail().get(SesionManager.PHOTO);
        String imageUrl = "http://edifarm.yoganova.my.id/" + po;
        Picasso.get().load(imageUrl).into(ephoto);


        updated = findViewById(R.id.update);
        updated.setOnClickListener(this);

        poto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat dialog untuk memilih sumber gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(edit_profile.this);
                builder.setTitle("Select Image Source");

                builder.setItems(new CharSequence[]{"Camera", "Gallery"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                // Memilih gambar dari kamera
                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                                break;
                            case 1:
                                // Memilih gambar dari galeri
                                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
                                break;
                        }
                    }
                });
                builder.show();
            }
        });

        Log.d("ID_USER", sesionManager.getUserDetail().get(SesionManager.USERNAME));
        eusername = findViewById(R.id.editusername);
        if (SesionManager.USERNAME == null) {
            eusername.setHint("Masukan Username");
        } else {
            eusername.setHint(sesionManager.getUserDetail().get(SesionManager.USERNAME));
        }


        ename = findViewById(R.id.editnama);
        if (SesionManager.NAME == null) {
            ename.setHint("Masukan Nama");
        } else {
            ename.setHint(sesionManager.getUserDetail().get(SesionManager.NAME));
        }

        eemail = findViewById(R.id.editemail);
        if (SesionManager.EMAIL == null) {
            eemail.setHint("Masukan Email");
        } else {
            eemail.setHint(sesionManager.getUserDetail().get(SesionManager.EMAIL));
        }

        eaddress = findViewById(R.id.editalamat);
        if (SesionManager.ADDRESS == null) {
            eaddress.setHint("Masukan Alamat");
        } else {
            eaddress.setHint(sesionManager.getUserDetail().get(SesionManager.ADDRESS));
        }

        ephone = findViewById(R.id.editnohp);
        if (SesionManager.PHONE == null) {
            ephone.setHint("Masukan Nomer Handphone");
        } else {
            ephone.setHint(sesionManager.getUserDetail().get(SesionManager.PHONE));
        }

        eborn_date = findViewById(R.id.edittgllahir);
        if (SesionManager.BORN_DATE == null) {
            eborn_date.setHint("Masukan Tanggal Lahir");
        } else {
            eborn_date.setHint(sesionManager.getUserDetail().get(SesionManager.BORN_DATE));
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.update:

                Id = sesionManager.getUserDetail().get(SesionManager.ID);
                Username = eusername.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.USERNAME) : eusername.getText().toString();
                Name = ename.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.NAME) : ename.getText().toString();
                Address = eaddress.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.ADDRESS) : eaddress.getText().toString();
                Phone = ephone.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.PHONE) : ephone.getText().toString();
                Born_date = eborn_date.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.BORN_DATE) : eborn_date.getText().toString();
                Email = eemail.getText().toString().isEmpty() ? sesionManager.getUserDetail().get(SesionManager.EMAIL) : eemail.getText().toString();

                // Mendapatkan foto dari ImageView
                Bitmap photoBitmap = ((BitmapDrawable) ephoto.getDrawable()).getBitmap();

                // Mengirim data ke API
                sendDataToApi(Id, Username, Name, Address, Phone, Born_date, Email, photoBitmap);

                break;

        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE && data != null) {
                // Memilih gambar dari kamera
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ephoto.setImageBitmap(photo);
                // Mengirim data gambar ke API (lakukan implementasi sendiri)
                // sendImageToApi(photo);
            } else if (requestCode == GALLERY_REQUEST_CODE && data != null) {
                // Memilih gambar dari galeri
                Uri selectedImage = data.getData();
                try {
                    Bitmap photo = MediaStore.Images.Media.getBitmap(edit_profile.this.getContentResolver(), selectedImage);
                    ephoto.setImageBitmap(photo);

                    // Mengirim data gambar ke API (lakukan implementasi sendiri)
                    // sendImageToApi(photo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void sendDataToApi(String user_id, String username, String name,
                               String address, String phone,
                               String born_date, String email, Bitmap photoBitmap) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        RequestBody requestBody;
        MultipartBody.Part photo = null;

        // Mengecek apakah ada foto yang dipilih
        if (photoBitmap != null) {
            // Jika ada foto, mengirim foto tersebut ke API
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photoBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            requestBody = RequestBody.create(MediaType.parse("image/*"), imageBytes);
            photo = MultipartBody.Part.createFormData("photo", "photo.jpg", requestBody);
        } else {
            // Jika tidak ada foto, menggunakan foto dari session manager
            String userPhoto = sesionManager.getUserDetail().get(SesionManager.PHOTO);
            requestBody = RequestBody.create(MediaType.parse("text/plain"), userPhoto);
        }

        RequestBody requestBodyUserId = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody requestBodyUsername = RequestBody.create(MediaType.parse("text/plain"), username);
        RequestBody requestBodyName = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody requestBodyAddress = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody requestBodyPhone = RequestBody.create(MediaType.parse("text/plain"), phone);
        RequestBody requestBodyBornDate = RequestBody.create(MediaType.parse("text/plain"), born_date);
        RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("text/plain"), email);

        Call<UpdateBio> call = apiInterface.updateResponse(requestBodyUserId, requestBodyUsername, requestBodyName, requestBodyAddress, requestBodyPhone, requestBodyBornDate, requestBodyEmail, photo);
        call.enqueue(new Callback<UpdateBio>() {
            @Override
            public void onResponse(Call<UpdateBio> call, Response<UpdateBio> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    final Dialog dialog = new Dialog(edit_profile.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.pop_up_done);
                    Button oke = dialog.findViewById(R.id.done);
                    TextView massage = dialog.findViewById(R.id.massegedone);

                    oke.setText("Oke");
                    massage.setText("Berhasil Merubah Data");
                    oke.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sesionManager.logoutSession();
                            Intent intent = new Intent(edit_profile.this, login.class);

                            startActivity(intent);
                        }
                    });
                    dialog.show();
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                    dialog.getWindow().setGravity(Gravity.CENTER);
                } else {
                    final Dialog dialog = new Dialog(edit_profile.this);
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
            }

            @Override
            public void onFailure(Call<UpdateBio> call, Throwable throwable) {
                Toast.makeText(edit_profile.this, "Update failed: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}