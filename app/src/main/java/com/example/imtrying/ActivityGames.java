package com.example.imtrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.imtrying.Models.Game;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityGames extends AppCompatActivity {

    RelativeLayout root;
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


        root = findViewById(R.id.games_activity);
        //
        // Подключение Firebase таблицы Game
        //Немного не то - он добавляет не запись, а поля

        FirebaseDatabase firebaseDatabase;


        DatabaseReference databaseReference;

        Game games = new Game();
        firebaseDatabase = FirebaseDatabase.getInstance();


        databaseReference = firebaseDatabase.getReference("Games");
        String name = "Разгонялки";
        String description = "Догонялки Описание";
        String type = "5-минутка";
        String year = "Любой";
        Integer time = 5;
        games.setName(name);
        games.setDescription(description);
        games.setType(type);
        games.setYear(year);
        games.setTime(time);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(FirebaseDatabase.getInstance().getReference().toString())
                        .setValue(games)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Snackbar.make(root,"Регистрация выполнена"
                                        ,Snackbar.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(root,"Ошибка регистрации. "
                                        + e.getMessage(),Snackbar.LENGTH_LONG).show();
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityGames.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });

        //
        //
        //





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
                Intent intent = new Intent(ActivityGames.this, ActivityMenu.class);
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
                        intent = new Intent(ActivityGames.this, ActivityTollBox.class);
                        startActivity(intent);
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
        Intent intent = new Intent(this, ActivityAddGames.class);
        startActivity(intent);
    }

}