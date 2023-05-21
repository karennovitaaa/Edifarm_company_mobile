package com.aditiyagilang.edifarm_company.dashboardfixx;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.databinding.FragmentNewPostingBinding;
import com.aditiyagilang.edifarm_company.model.Posting.Posting;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPosting extends Fragment {

    private static final int MAP_REQUEST_CODE = 2;
    private static final int CAMERA_REQUEST_CODE = 3;
    private static final int GALLERY_REQUEST_CODE = 4;
    ImageView gbr_add;
    ImageButton location;
    EditText text_location;
    EditText caption;
    Button add_post;
    ImageButton add_image;
    ApiInterface apiInterface;
    SesionManager sesionManager;
    private FragmentNewPostingBinding binding;
    private double latitude; // Declare latitude as a field
    private double longitude; // Declare longitude as a field

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNewPostingBinding.inflate(inflater, container, false);
        sesionManager = new SesionManager(getActivity().getApplicationContext());
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gbr_add = getView().findViewById(R.id.addImage);
        location = getView().findViewById(R.id.callocation);
        text_location = getView().findViewById(R.id.add_location);
        caption = getView().findViewById(R.id.add_caption);
        add_post = getView().findViewById(R.id.add_posting);
        add_image = getView().findViewById(R.id.addImage);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String User_Id = sesionManager.getUserDetail().get(SesionManager.ID);
//
        String lokasi;

        LocationManager locationManager;
        LocationListener locationListener;
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        final int REQUEST_LOCATION_PERMISSION = 1;
        // Inisialisasi LocationListener
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Mendapatkan data lokasi latitude dan longitude
                // Mendapatkan data lokasi latitude dan longitude
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
// Mendapatkan nama lokasi berdasarkan latitude dan longitude menggunakan Reverse Geocoding
                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    if (addresses != null && addresses.size() > 0) {
                        Address address = addresses.get(0);
                        String locationName = address.getAddressLine(0);

                        // Menyimpan data lokasi dalam variabel
                        String locationString = "Location: " + locationName;

                        // Menampilkan data lokasi dalam EditText text_location
                        text_location.setText(locationString);

                        // Menyimpan latitude dan longitude dalam variabel
                        String latitudeString = "Latitude: " + latitude;
                        String longitudeString = "Longitude: " + longitude;
                        NewPosting.this.latitude = latitude;
                        NewPosting.this.longitude = longitude;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };

        // Mengatur OnClickListener pada tombol callocation
        binding.callocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Periksa izin lokasi
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {
                    // Izin lokasi diberikan, dapatkan lokasi pengguna
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                } else {
                    // Izin lokasi tidak diberikan, minta izin
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION},
                            REQUEST_LOCATION_PERMISSION);
                }
            }
        });
        binding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat dialog untuk memilih sumber gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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


        binding.addPosting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String captionText = caption.getText().toString();
                String locationText = text_location.getText().toString();
                String latitudeText = ""; // Menyimpan nilai latitude dalam string
                String longitudeText = ""; // Menyimpan nilai longitude dalam string

                // Mengubah nilai latitude dan longitude menjadi string
                latitudeText = String.valueOf(latitude);
                longitudeText = String.valueOf(longitude);

                Bitmap imageBitmap = ((BitmapDrawable) gbr_add.getDrawable()).getBitmap();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();

                RequestBody captionBody = RequestBody.create(MediaType.parse("text/plain"), captionText);
                RequestBody locationBody = RequestBody.create(MediaType.parse("text/plain"), locationText);
                RequestBody latitudeBody = RequestBody.create(MediaType.parse("text/plain"), latitudeText);
                RequestBody longitudeBody = RequestBody.create(MediaType.parse("text/plain"), longitudeText);
                RequestBody user_idBody = RequestBody.create(MediaType.parse("text/plain"), User_Id);

                RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), imageBytes);
                MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", "image.jpg", imageBody);
                Toast.makeText(getContext(), captionBody.toString() + latitudeBody + longitudeBody + user_idBody, Toast.LENGTH_SHORT).show();
                // Panggil kembali API setiap 5 detik
                Call<Posting> ActCall = apiInterface.postResponse(captionBody, latitudeBody, longitudeBody, user_idBody, imagePart);

                ActCall.enqueue(new Callback<Posting>() {
                    @Override
                    public void onResponse(Call<Posting> call, Response<Posting> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            Toast.makeText(getContext(), response.body().getMessage() + "Ihiy Update Status", Toast.LENGTH_SHORT).show();
                            NavHostFragment.findNavController(NewPosting.this)
                                    .navigate(R.id.action_NewPost_to_FirstFragment);

                        } else {
                            Toast.makeText(getContext(), "Goblok", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Posting> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


            }
        });
    }


    private File getFileFromUri(Uri uri) {
        String filePath = null;
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            filePath = cursor.getString(columnIndex);

            cursor.close();
        }

        if (filePath != null) {
            return new File(filePath);
        }

        return null;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE && data != null) {
                // Memilih gambar dari kamera
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                gbr_add.setImageBitmap(photo);
                // Mengirim data gambar ke API (lakukan implementasi sendiri)
                // sendImageToApi(photo);
            } else if (requestCode == GALLERY_REQUEST_CODE && data != null) {
                // Memilih gambar dari galeri
                Uri selectedImage = data.getData();
                try {
                    Bitmap photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    gbr_add.setImageBitmap(photo);
                    // Mengirim data gambar ke API (lakukan implementasi sendiri)
                    // sendImageToApi(photo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}