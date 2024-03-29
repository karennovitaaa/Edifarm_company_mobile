package com.aditiyagilang.edifarm_company.Biographical;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aditiyagilang.edifarm_company.databinding.FragmentBiopostBinding;

public class BioPost extends Fragment {

    private FragmentBiopostBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentBiopostBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(BioPost.this)
//                        .navigate(com.aditiyagilang.edifarm_company.R.id.action_First3Fragment_to_Second3Fragment);
//            }
//        });

 
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}