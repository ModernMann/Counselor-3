package com.example.imtrying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityAddGames extends AppCompatActivity {

    //Инициализируем поля ввода и Кнопки
    EditText name, description, type, time, year;
    Button saveGame, cancelGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_games);


        //Определяем поля ввода
        name = findViewById(R.id.textName);
        description = findViewById(R.id.textDescription);
        type = findViewById(R.id.textType);
        time = findViewById(R.id.textTime);
        year = findViewById(R.id.textYear);

        //Определяем кнопки
        saveGame = findViewById(R.id.buttonSave);
        cancelGame = findViewById(R.id.buttonCancel);


        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(ActivityAddGames.this);
                myDB.addGame(name.getText().toString().trim(),
                        description.getText().toString().trim(),
                        type.getText().toString().trim(),
                        time.getText().toString().trim(),
                        year.getText().toString().trim()
                        );
                Toast.makeText(ActivityAddGames.this, "Спасибо! Ваша игра отправлена на рассмотрение", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityAddGames.this, ActivityGames.class);
                startActivity(intent);
            }
        });

        cancelGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAddGames.this, ActivityGames.class);
                startActivity(intent);
            }
        });
    }

}