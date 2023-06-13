package com.example.imtrying.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.example.imtrying.Models.User;
import com.example.imtrying.R;
import com.example.imtrying.databinding.FragmentToolboxBinding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
            if (day <= 3){
                binding.textPeriod.setText("Организационный");
            }
            else if (day > 3 && day <= 18){
                binding.textPeriod.setText("Основной");
            }
            else if (day > 18 ){
                binding.textPeriod.setText("Заключительный");
            }
        }
        catch(Exception ex){
            Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }

        binding.btnDice.setOnClickListener(v -> navigateTo(user, new DiceFragment()));
        binding.btnDraw.setOnClickListener(v -> navigateTo(user, new LotteryFragment()));
    }

    private void navigateTo(User user, Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        fragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.bottomNavHostFragment, fragment)
                .commit();
    }

}