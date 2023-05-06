package com.aditiyagilang.edifarm_company;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class edit_profile extends AppCompatActivity {
    private TextView textViewPassword;
    private TextView textViewConfirmPassword;
    private Button buttonSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

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

    }
}