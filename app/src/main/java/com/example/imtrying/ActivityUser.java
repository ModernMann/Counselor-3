package com.example.imtrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Date;

public class ActivityUser extends AppCompatActivity {

    BottomNavigationView bnv;
    MyDatabaseHelper myDB;
    TextView textDate, textPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        DateAndPeriod();
    }
    void DateAndPeriod(){
        //Дата начала смены
        Date dateStart = new Date(2023,2,11) ;

        textDate = findViewById(R.id.textDate);
        textPeriod = findViewById(R.id.textPeriod);



        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        Date curDate = new Date();
        textDate.setText(sdf.format(curDate).toString());

        Integer timeBetween = (int)(long)(curDate.getTime() / 1000 - dateStart.getTime() / 1000 );
        if (timeBetween > 0 && timeBetween <= 259200000){
           textPeriod.setText("Основной период");
        }
        else if (timeBetween > 259200000 && timeBetween <= 1555200000){
            textPeriod.setText("Организационный период");
        }
        else if (timeBetween > 1555200000 && timeBetween < 1814400000){
            textPeriod.setText("Заключительный период");
        }



        //
        // Нижнее меню навигации и его действия
        //
        bnv = findViewById(R.id.bottomNavigationView3);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                switch(id){
                    case R.id.action_user:
                        break;
                    case R.id.action_book:
                        intent = new Intent(ActivityUser.this, MainActivity.class);
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

}