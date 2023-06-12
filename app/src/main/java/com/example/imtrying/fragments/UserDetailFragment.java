package com.example.imtrying.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imtrying.activities.ActivityEditSchedule;
import com.example.imtrying.databinding.FragmentUserDetailBinding;


public class UserDetailFragment extends Fragment {

    private FragmentUserDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DateAndPeriod();
    }

    void DateAndPeriod(){
        binding.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityEditSchedule.class);
            startActivity(intent);
        });
    }

}