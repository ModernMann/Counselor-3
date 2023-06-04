package com.example.imtrying.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.imtrying.Models.User;
import com.example.imtrying.databinding.FragmentUserBinding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private Boolean showDetails = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        User user = (User) getArguments().getSerializable("user");
        assert user != null;
        binding.textUserName.setText(user.getName());
        binding.textTeam.setText(user.getTeam());

        //Вывести текущую дату
        binding.textDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        //Вывести период смены
        ////Дата начала смены
        LocalDate dateStart = LocalDate.parse("2023-02-01") ;
        try{
            LocalDate curDate = LocalDate.now();
            binding.textDate.setText(curDate.toString());
            // Вычитание даты из даты
            long day = ChronoUnit.DAYS.between(dateStart, curDate);
            if (day <= 3) {
                binding.textPeriod.setText("Организационный");
            }
            else if (day > 3 && day <= 18) {
                binding.textPeriod.setText("Основной");
            }
            else if (day > 18) {
                binding.textPeriod.setText("Заключительный");
            }
        }
        catch(Exception ex){
            Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }

        binding.imageUser.setOnClickListener(v -> {
            if (!showDetails) {
                getParentFragmentManager().beginTransaction()
                        .replace(binding.menuContainerView.getId(), new MainMenuFragment())
                        .commit();
            } else {
                getParentFragmentManager().beginTransaction()
                        .replace(binding.menuContainerView.getId(), new UserDetailFragment())
                        .commit();
            }
            showDetails = !showDetails;
        });
        binding.imageUser.callOnClick();
    }

}