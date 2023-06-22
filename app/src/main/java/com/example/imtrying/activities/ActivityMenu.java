package com.example.imtrying.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.example.imtrying.Models.User;
import com.example.imtrying.R;
import com.example.imtrying.databinding.ActivityMenuBinding;
import com.example.imtrying.fragments.ToolBoxFragment;
import com.example.imtrying.fragments.UserFragment;
import com.google.firebase.auth.FirebaseAuth;

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
                    navigateTo(user, new UserFragment());
                    break;
                case R.id.action_toolbox:
                    navigateTo(user, new ToolBoxFragment());
                    break;

            }
            return true;
        });
        navigateTo(user, new UserFragment());
        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() == null) {
                Intent intent = new Intent(ActivityMenu.this, MainActivity.class);
                getPrefs().edit()
                        .putBoolean(com.example.imtrying.store.SharedPreferences.IS_SIGN_IN_KEY, false)
                        .apply();
                startActivity(intent);
                finish();
            }
        });
    }

    private void navigateTo(User user, Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(binding.bottomNavHostFragment.getId(), fragment)
                .commit();
        binding.getRoot().setBackground(AppCompatResources.getDrawable(this, R.drawable.background_menu));
    }

    private SharedPreferences getPrefs() {
        return getSharedPreferences(com.example.imtrying.store.SharedPreferences.STORE_NAME, Context.MODE_PRIVATE);
    }

}