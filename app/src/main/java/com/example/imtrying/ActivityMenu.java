package com.example.imtrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imtrying.Models.User;
import com.example.imtrying.firebase.Provider;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ActivityMenu extends AppCompatActivity {

    private ListView listMenu;
    private String[] arrayMenu;
    private ArrayAdapter<String> adapter;
    BottomNavigationView bnv;
    Button btnGame, btnCandle, btnBook;
    TextView textUserName, textDate, textPeriod, textTeam;
    ImageView imageUser;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnGame = findViewById(R.id.btnGame);
        btnCandle = findViewById(R.id.btnCandle);
        btnBook = findViewById(R.id.btnBook);
        textUserName = findViewById(R.id.textUserName);
        textDate = findViewById(R.id.textDate);
        textPeriod = findViewById(R.id.textPeriod);
        textTeam = findViewById(R.id.textTeam);
        imageUser = findViewById(R.id.imageUser);

        //Вывести номер отряда
        //textTeam.setText();
        //Вывести имя пользователя
        //Log.d(this.getClass().getName(), "username -> " +  user.getDisplayName());

        //Вывести текущую дату
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String dateString = sdf.format(date);
        textDate.setText(dateString);
        //Вывести период смены
        ////Дата начала смены
        LocalDate dateStart = LocalDate.parse("2023-02-01") ;
        try{
            LocalDate curDate = LocalDate.now();
            textDate.setText(curDate.toString());
            // Вычитание даты из даты
            long day = ChronoUnit.DAYS.between(dateStart, curDate);
            if (day <= 3){
                textPeriod.setText("Организационный");
            }
            else if (day > 3 && day <= 18){
                textPeriod.setText("Основной");
            }
            else if (day > 18 ){
                textPeriod.setText("Заключительный");
            }
        }
        catch(Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }





        imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this, ActivityUser.class);
                startActivity(intent);
            }
        });

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this, ActivityGames.class);
                startActivity(intent);
            }
        });
        btnCandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this, ActivityCandles.class);
                startActivity(intent);
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // позже
            }
        });



        //
        // Нижнее меню навигации и его действия
        //
        bnv = findViewById(R.id.bottomNavigationView6);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                switch(id){
                    case R.id.action_user:
                        intent = new Intent(ActivityMenu.this, ActivityUser.class);
                        startActivity(intent);
                        break;
                    case R.id.action_book:
                        break;
                    case R.id.action_toolbox:
                        intent = new Intent(ActivityMenu.this, ActivityTollBox.class);
                        startActivity(intent);
                        break;

                }
                return true;
            }
        });
        //
        //------------------------------------------------------------------------------
    }

}
