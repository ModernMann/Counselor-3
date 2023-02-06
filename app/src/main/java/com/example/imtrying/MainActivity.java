package com.example.imtrying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickEnter(View view) {
        //Авторизация
        Intent intent = new Intent(this, ActivityGames.class);
        startActivity(intent);
    }

    public void onClickRegistration(View view) {
        //Регистрация
        Intent intent = new Intent(this, ActivityRegistration.class);
        startActivity(intent);
    }
}