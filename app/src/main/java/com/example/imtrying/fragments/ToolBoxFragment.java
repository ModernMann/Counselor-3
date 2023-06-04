package com.example.imtrying.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.imtrying.R;
import com.example.imtrying.databinding.FragmentToolBoxBinding;

public class ToolBoxFragment extends Fragment {

    private FragmentToolBoxBinding binding;
    private String[] arrayMenu;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentToolBoxBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrayMenu = getResources().getStringArray(R.array.Toolbox);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrayMenu);
        binding.listToolBox.setAdapter(adapter);

        binding.listToolBox.setOnItemClickListener((parent, view1, position, id) -> {
            try {
                switch(position){
                    case 0 :
                        navigate(new DiceFragment());
                        break;
                    case 1:
                        navigate(new LotteryFragment());
                        break;
                    case 2:
                        // Soon
                        break;
                }
            }
            catch(Exception ex){
                Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigate(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.bottomNavHostFragment, fragment)
                .commit();
    }

}
