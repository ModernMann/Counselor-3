package com.example.imtrying.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.example.imtrying.Models.User;
import com.example.imtrying.R;
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
        //Вывести имя пользователя
        User user = (User) getIntent().getSerializableExtra("user");
        //
        // Нижнее меню навигации и его действия
        //
        binding.bottomNavigationView6.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.action_user:
                    setUserFragment(user);
                    break;
                case R.id.action_book:
                    break;
                case R.id.action_toolbox:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bottomNavHostFragment, new ToolBoxFragment())
                            .commit();
                    binding.getRoot().setBackground(AppCompatResources.getDrawable(this, R.drawable.backgrondimg));
                    break;

            }
            return true;
        });
        setUserFragment(user);
    }

    private void setUserFragment(User user) {
        Fragment fragment = new UserFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(binding.bottomNavHostFragment.getId(), fragment)
                .commit();
        binding.getRoot().setBackground(AppCompatResources.getDrawable(this, R.drawable.background_menu));
    }

}