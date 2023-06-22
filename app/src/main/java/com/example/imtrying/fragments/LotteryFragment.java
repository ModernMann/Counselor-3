package com.example.imtrying.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.imtrying.Models.User;
import com.example.imtrying.R;
import com.example.imtrying.databinding.FragmentLotteryBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotteryFragment extends Fragment {

    private FragmentLotteryBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLotteryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        User user = (User) getArguments().getSerializable("user");
        assert user != null;

        binding.include2.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        binding.include2.toolbar.setNavigationOnClickListener(v -> navigateTo(user, new ToolBoxFragment()));
        Glide.with(getContext()).load(R.drawable.applogo).into(binding.imageAnimals);
        binding.btnStart.setEnabled(true);
        binding.btnNext.setEnabled(false);
        binding.btnReload.setEnabled(false);
        List<Integer> arrayAnimals = new ArrayList<>();
        final Integer[] CountPerson = new Integer[1];
        final Integer[] listNum = {0};
        final Integer[] TeamCount = {0};


        binding.btnStart.setOnClickListener(v -> {
            try{
                final int[] pictures = {
                        R.drawable.dog, R.drawable.cat, R.drawable.dragon,
                        R.drawable.duck, R.drawable.fish, R.drawable.frog
                };

                Integer personCount = Integer.parseInt(binding.textPersonCount.getText().toString());
                CountPerson[0] = personCount;
                Integer teamCount = Integer.parseInt(binding.textTeamCount.getText().toString());
                TeamCount[0] = teamCount;

                // добавить проверку на пустые поля

                    for (int j = 0; j < teamCount; j++){
                        for (int i = 0; i < personCount / teamCount; i++){
                            arrayAnimals.add(pictures[j]);
                        }
                    }
                    Collections.shuffle(arrayAnimals);
                    binding.btnStart.setEnabled(false);
                    binding.btnNext.setEnabled(true);
                    binding.btnReload.setEnabled(true);


            }
            catch(Exception ex){
                Toast.makeText(getContext(), "Введите кол-во команд и участников", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnReload.setOnClickListener(v -> {
            binding.btnStart.setEnabled(true);
            binding.btnReload.setEnabled(false);
            binding.btnNext.setEnabled(false);
            arrayAnimals.clear();
            listNum[0] = 0;
            CountPerson[0] = 0;
            Glide.with(getContext()).load(R.drawable.applogo).into(binding.imageAnimals);
            binding.textList.setText("");
            binding.textPersonCount.setText("");
            binding.textTeamCount.setText("");
        });

        binding.btnNext.setOnClickListener(v -> {
            try {
                binding.btnNext.setText("Далее");
                if (listNum[0] <= CountPerson[0]/TeamCount[0]*TeamCount[0] - 1) {
                    Glide.with(getContext())
                            .asGif()
                            .load(arrayAnimals.get(listNum[0]))
                            .into(binding.imageAnimals);
                    binding.textList.setText((listNum[0] + 1) + " из " + CountPerson[0]/TeamCount[0]*TeamCount[0]);
                    listNum[0]++;
                }
                else {
                    binding.btnNext.setEnabled(false);
                    binding.btnReload.setEnabled(true);
                    binding.btnNext.setText("Начать");
                }
            }
            catch(Exception ex){
                Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
            }

        });
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