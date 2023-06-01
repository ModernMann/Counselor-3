package com.example.imtrying;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.imtrying.Models.User;
import com.example.imtrying.databinding.ActivityMenuBinding;
import com.example.imtrying.fragments.ToolBoxFragment;
import com.example.imtrying.fragments.UserFragment;

public class ActivityMenu extends AppCompatActivity {

    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Вывести номер отряда
        //textTeam.setText();
        //Вывести имя пользователя
        User user = (User) getIntent().getSerializableExtra("user");

        //
        // Нижнее меню навигации и его действия
        //
        binding.bottomNavigationView6.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_user:
                    setUserFragment(user);
                    break;
                case R.id.action_book:
                    break;
                case R.id.action_toolbox:
                    getSupportFragmentManager().beginTransaction()
                            .replace(binding.fragmentContainerView.getId(), new ToolBoxFragment())
                            .commit();
                    binding.getRoot().setBackground(getDrawable(R.drawable.background_menu));
                    break;
            }
            return true;
        });
        setUserFragment(user);
        //
        //------------------------------------------------------------------------------
    }

    private void setUserFragment(User user) {
        Fragment fragment = new UserFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainerView.getId(), fragment)
                .commit();
        binding.getRoot().setBackground(getDrawable(R.drawable.background_menu));
    }

}