package com.example.imtrying.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imtrying.ActivityCandles;
import com.example.imtrying.ActivityGames;
import com.example.imtrying.R;
import com.example.imtrying.databinding.FragmentMainMenuBinding;

public class MainMenuFragment extends Fragment {

    private FragmentMainMenuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnGame.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityGames.class);
            startActivity(intent);
        });
        binding.btnCandle.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityCandles.class);
            startActivity(intent);
        });
        binding.btnBook.setOnClickListener(v -> {
            // позже
        });
    }
}