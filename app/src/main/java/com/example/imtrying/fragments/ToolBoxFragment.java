package com.example.imtrying.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.imtrying.ActivityCandles;
import com.example.imtrying.ActivityDraw;
import com.example.imtrying.ActivityGames;
import com.example.imtrying.R;
import com.example.imtrying.DiceActivity;
import com.example.imtrying.databinding.FragmentToolboxBinding;

public class ToolBoxFragment extends Fragment {

    private FragmentToolboxBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentToolboxBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnDice.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DiceActivity.class);
            startActivity(intent);
        });
        binding.btnDraw.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityDraw.class);
            startActivity(intent);
        });
    }
}