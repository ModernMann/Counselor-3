package com.example.imtrying.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imtrying.Models.DataClass;
import com.example.imtrying.R;
import com.example.imtrying.activities.UploadActivity;
import com.example.imtrying.databinding.FragmentCandlesBinding;
import com.example.imtrying.firebase.Database;
import com.example.imtrying.store.MyAdapter;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CandlesFragment extends Fragment {
    private FragmentCandlesBinding binding;
    private List<DataClass> dataList;
    private MyAdapter adapter;
    private ValueEventListener listener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCandlesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();
        adapter = new MyAdapter(getContext(), dataList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(adapter);

        listener = Database.fetchCandles(candles -> {
            dataList.clear();
            dataList.addAll(candles);
            adapter.notifyDataSetChanged();
            dialog.dismiss();
        }, dialog::dismiss);

        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchListGame(newText);
                return true;
            }
        });
        binding.fabButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UploadActivity.class);
            startActivity(intent);
        });
    }

    private void searchListGame(String text) {
        ArrayList<DataClass> searchList = new ArrayList<>();
        for (DataClass dataClass : dataList){
            if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }

    @Override
    public void onDestroy() {
        Database.removeGamesListener(listener);
        super.onDestroy();
    }

}