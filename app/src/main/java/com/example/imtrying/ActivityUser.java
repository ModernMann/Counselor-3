package com.example.imtrying;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ActivityUser extends AppCompatActivity {

    BottomNavigationView bnv;
    TextView textDate, textPeriod;
    Button editSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        DateAndPeriod();
    }
    void DateAndPeriod(){


        //Дата начала смены
        LocalDate dateStart = LocalDate.parse("2023-02-01") ;


        textDate = findViewById(R.id.textDate);
        textPeriod = findViewById(R.id.textPeriod);
        editSchedule = findViewById(R.id.butEditSchedule);

        try{
            LocalDate curDate = LocalDate.now();
            textDate.setText(curDate.toString());
            // Вычитание даты из даты
            long day = ChronoUnit.DAYS.between(dateStart, curDate);
            if (day <= 3){
                textPeriod.setText("Организационный период");
            }
            else if (day > 3 && day <= 18){
                textPeriod.setText("Основной период");
            }
            else if (day > 18 ){
                textPeriod.setText("Заключительный период");
            }
        }
        catch(Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }





        //
        // Нижнее меню навигации и его действия
        //
        bnv = findViewById(R.id.bottomNavigationView5);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                switch(id){
                    case R.id.action_user:
                        break;
                    case R.id.action_book:
                        intent = new Intent(ActivityUser.this, ActivityMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.action_toolbox:
                        intent = new Intent(ActivityUser.this, ActivityTollBox.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        //
        //------------------------------------------------------------------------------




    }

    public void OnClick(View view) {
        Intent intent;
        intent = new Intent(ActivityUser.this, ActivityEditSchedule.class);
        startActivity(intent);
    }
}