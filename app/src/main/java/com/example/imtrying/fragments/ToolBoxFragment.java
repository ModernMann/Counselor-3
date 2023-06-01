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

import com.example.imtrying.ActivityDraw;
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
        String[] arrayMenu = getResources().getStringArray(R.array.Toolbox);
        binding.listToolBox.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrayMenu));
        binding.listToolBox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    switch(position){
                        case 0 :
                            intent = new Intent(getContext(), DiceActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(getContext(), ActivityDraw.class);
                            startActivity(intent);
                            break;
                        case 2:
                            break;
                    }
                }
                catch(Exception ex){
                    Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}