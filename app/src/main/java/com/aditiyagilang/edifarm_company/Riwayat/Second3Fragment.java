package com.aditiyagilang.edifarm_company.Riwayat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.databinding.FragmentHistoryBinding;

public class Second3Fragment extends Fragment {

    private FragmentHistoryBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Second3Fragment.this)
                        .navigate(R.id.action_Second3Fragment_to_FirstFragment);
            }
        });

  
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}