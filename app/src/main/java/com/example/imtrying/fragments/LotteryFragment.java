package com.example.imtrying.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        binding.include2.toolbar.setNavigationIcon(R.drawable.arrow_back);
        binding.include2.toolbar.setNavigationOnClickListener(v -> getParentFragmentManager().beginTransaction()
                .replace(R.id.bottomNavHostFragment, new ToolBoxFragment())
                .commit());

        binding.btnStart.setEnabled(true);
        binding.btnNext.setEnabled(false);
        binding.btnReload.setEnabled(false);
        List<Integer> arrayAnimals = new ArrayList<>();
        final Integer[] CountPerson = new Integer[1];
        final Integer[] listNum = {0};


        binding.btnStart.setOnClickListener(v -> {
            try{
                final int pictures[] = {R.drawable.pic_bird, R.drawable.pic_dog, R.drawable.pic_cat,
                        R.drawable.pic_fish, R.drawable.pic_frog, R.drawable.pic_rabbit, R.drawable.pic_turtle,
                        R.drawable.pic_tiger, R.drawable.pic_pinguin};

                Integer personCount = Integer.parseInt(binding.textPersonCount.getText().toString());
                CountPerson[0] = personCount;
                Integer teamCount = Integer.parseInt(binding.textTeamCount.getText().toString());

                // добавить проверку на пустые поля
                if (personCount % teamCount == 0) {
                    for (int j = 0; j < teamCount; j++){
                        for (int i = 0; i < personCount / teamCount; i++){
                            arrayAnimals.add(pictures[j]);
                        }
                    }
                    Collections.shuffle(arrayAnimals);
                    binding.btnStart.setEnabled(false);
                    binding.btnNext.setEnabled(true);
                }
                else {
                    Toast.makeText(getContext(), "Поделить невозможно", Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception ex){
                Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnReload.setOnClickListener(v -> {
            binding.btnStart.setEnabled(true);
            binding.btnReload.setEnabled(false);
            binding.btnNext.setEnabled(false);
            arrayAnimals.clear();
            listNum[0] = 0;
            CountPerson[0] = 0;
            binding.imageAnimals.setImageResource(R.drawable.pic_empty);
            binding.textList.setText("");
            binding.textPersonCount.setText("");
            binding.textTeamCount.setText("");
        });

        binding.btnNext.setOnClickListener(v -> {
            try{
                binding.btnNext.setText("Далее");
                if (listNum[0]<=CountPerson[0]-1){
                    binding.imageAnimals.setImageResource(arrayAnimals.get(listNum[0]));
                    binding.textList.setText((listNum[0] + 1) + " из " + CountPerson[0]);
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
}
