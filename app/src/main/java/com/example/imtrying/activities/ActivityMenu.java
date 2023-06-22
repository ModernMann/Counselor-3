package com.example.imtrying.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.example.imtrying.Models.User;
import com.example.imtrying.R;
import com.example.imtrying.databinding.ActivityMenuBinding;
import com.example.imtrying.firebase.Database;
import com.example.imtrying.fragments.MainMenuFragment;
import com.example.imtrying.fragments.ToolBoxFragment;
import com.example.imtrying.fragments.UserDetailFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ActivityMenu extends AppCompatActivity {

    private ActivityMenuBinding binding;
    private Boolean showDetails = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Вывести имя пользователя
        User user = (User) getIntent().getSerializableExtra("user");
        boolean isAdmin = Integer.parseInt(user.getTeam()) == 0;
        binding.textUserName.setText(user.getName());
        if (isAdmin){
            binding.textTeam.setText("Админ");
        }
        else{
            binding.textTeam.setText(user.getTeam()+" отряд");
        }


        //Вывести текущую дату
        binding.textDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MM ")));
        //Вывести период смены

        ////Дата начала смены
        LocalDate dateStart = LocalDate.parse("2023-06-22") ;

        try {
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
        } catch(Exception ex) {
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }

        binding.imageUser.setOnClickListener(v -> {
            if (!showDetails) {
                getSupportFragmentManager().beginTransaction()
                        .replace(binding.bottomNavHostFragment.getId(), new MainMenuFragment())
                        .commit();
            } else {
                UserDetailFragment fragment = new UserDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .replace(binding.bottomNavHostFragment.getId(), fragment)
                        .commit();
            }
            showDetails = !showDetails;
        });
        binding.imageUser.callOnClick();
        binding.logoutBtn.setOnClickListener(v -> Database.logout());
        //
        // Нижнее меню навигации и его действия
        //
        binding.bottomNavigationView6.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.action_user:
                    navigateTo(user, new MainMenuFragment());
                    break;
                case R.id.action_toolbox:
                    navigateTo(user, new ToolBoxFragment());
                    break;
                case R.id.action_schedule:
                    navigateTo(user, new UserDetailFragment());
                    break;

            }
            return true;
        });
        navigateTo(user, new MainMenuFragment());
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