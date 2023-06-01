package com.example.imtrying.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imtrying.ActivityEditSchedule;
import com.example.imtrying.ActivityMenu;
import com.example.imtrying.ActivityTollBox;
import com.example.imtrying.ActivityUser;
import com.example.imtrying.R;
import com.example.imtrying.databinding.FragmentUserDetailBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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