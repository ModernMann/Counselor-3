package com.example.imtrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ActivityGames extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> game_name, game_description, game_type, game_time, game_year;
    Button buttonBack;
    BottomNavigationView bnv;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        recyclerView = findViewById(R.id.recyclerView);
        buttonBack = findViewById(R.id.buttonBack);

        myDB = new MyDatabaseHelper(ActivityGames.this);
        //if ()
        myDB.CheckDB();
        game_name = new ArrayList<>();
        game_description = new ArrayList<>();
        game_type = new ArrayList<>();
        game_time = new ArrayList<>();
        game_year = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ActivityGames.this,game_name,game_description,game_type
                ,game_time,game_year);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityGames.this));
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGames.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //
        // Нижнее меню навигации и его действия
        //
        bnv = findViewById(R.id.bottomNavigationView2);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                switch(id){
                    case R.id.action_user:
                        intent = new Intent(ActivityGames.this, ActivityUser.class);
                        startActivity(intent);
                        break;
                    case R.id.action_book:
                        intent = new Intent(ActivityGames.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_toolbox:
                        //Добавить активность Тулбоксов
                        break;

                }
                return true;
            }
        });
        //
        //------------------------------------------------------------------------------



    }
    //
    // Заполнение страницы
    //
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
        else {
            while(cursor.moveToNext()){
                game_name.add(cursor.getString(1));
                game_description.add(cursor.getString(2));
                game_type.add(cursor.getString(3));
                game_time.add(cursor.getString(4));
                game_year.add(cursor.getString(5));
            }
        }
    }
    //
    //
    //



    public void onClickAddGame(View view) {
        Intent intent = new Intent(this, ActivityRegistration.class);
        startActivity(intent);
    }

}