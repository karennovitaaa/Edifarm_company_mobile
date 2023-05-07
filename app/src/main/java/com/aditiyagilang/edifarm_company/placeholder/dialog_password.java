package com.aditiyagilang.edifarm_company.placeholder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.aditiyagilang.edifarm_company.R;

public class dialog_password extends AppCompatDialogFragment {
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    private Button button;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.dialog_confirm_password, null);

        builder.setView(view)
                .setTitle("..")
                .setNegativeButton("...", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                })
                .setPositiveButton("....", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });

        editTextPassword = view.findViewById(R.id.password);
        editTextConfirmPassword = view.findViewById(R.id.ulangi_password);


        return builder.create();
    }

}
