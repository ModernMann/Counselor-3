package com.example.imtrying.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imtrying.Models.Schedule;
import com.example.imtrying.activities.ActivityEditSchedule;
import com.example.imtrying.databinding.FragmentUserDetailBinding;
import com.example.imtrying.firebase.Database;
import com.google.firebase.database.ValueEventListener;


public class UserDetailFragment extends Fragment {

    private FragmentUserDetailBinding binding;
    private ValueEventListener listener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        boolean isAdmin = getArguments().getBoolean("isAdmin");
        if (isAdmin) {
            binding.editButton.setVisibility(View.VISIBLE);
            binding.editButton.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), ActivityEditSchedule.class);
                startActivity(intent);
            });
        } else {
            binding.editButton.setVisibility(View.GONE);
        }
        // Заполнение полей (берется из Firebase)
        listener = Database.fetchSchedule(schedules -> {
            fillData(schedules.get(0));
        }, () -> {/* OnError */});
    }

    private void fillData(Schedule schedule) {
        binding.textMorning.setText(schedule.getDataMorning());
        binding.textMidDay.setText(schedule.getDataMidDay());
        binding.textEvening.setText(schedule.getDataEvening());
    }

    @Override
    public void onDestroy() {
        Database.removeScheduleListener(listener);
        super.onDestroy();
    }
}