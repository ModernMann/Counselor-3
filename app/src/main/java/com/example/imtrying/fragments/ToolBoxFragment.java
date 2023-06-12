package com.example.imtrying.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.imtrying.R;
import com.example.imtrying.databinding.FragmentToolboxBinding;

public class ToolBoxFragment extends Fragment {

    private FragmentToolboxBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentToolboxBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnDice.setOnClickListener(v -> navigate(new DiceFragment()));
        binding.btnDraw.setOnClickListener(v -> navigate(new LotteryFragment()));
    }

    private void navigate(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.bottomNavHostFragment, fragment)
                .commit();
    }

}