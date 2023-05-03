package com.example.imtrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ActivityDraw extends AppCompatActivity {

    Button btnStart, btnReload, btnNext;
    BottomNavigationView bnv;
    public static ImageView imageAnimals;

    EditText textPersonCount, textTeamCount;
    TextView textList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        btnStart = findViewById(R.id.btnStart);
        btnReload = findViewById(R.id.btnReload);
        btnNext = findViewById(R.id.btnNext);
        textTeamCount = findViewById(R.id.textTeamCount);
        textPersonCount = findViewById(R.id.textPersonCount);
        imageAnimals = findViewById(R.id.imageAnimals);
        textList = findViewById(R.id.textList);

        btnStart.setEnabled(true);
        btnNext.setEnabled(false);
        btnReload.setEnabled(false);
        List<Integer> arrayAnimals = new ArrayList<>();
        final Integer[] CountPerson = new Integer[1];
        final Integer[] listNum = {0};




        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    final int pictures[] = {R.drawable.pic_bird, R.drawable.pic_dog, R.drawable.pic_cat,
                            R.drawable.pic_fish, R.drawable.pic_frog, R.drawable.pic_rabbit, R.drawable.pic_turtle,
                            R.drawable.pic_tiger, R.drawable.pic_pinguin};

                    Integer personCount = Integer.parseInt(textPersonCount.getText().toString());
                    CountPerson[0] = personCount;
                    Integer teamCount = Integer.parseInt(textTeamCount.getText().toString());

                    // добавть проверку на пустые поля
                    if (personCount%teamCount==0){

                        for (int j=0;j<teamCount;j++){
                            for (int i=0;i<personCount/teamCount;i++){
                                arrayAnimals.add(pictures[j]);
                            }
                        }
                        Collections.shuffle(arrayAnimals);
                        btnStart.setEnabled(false);
                        btnNext.setEnabled(true);
                    }
                    else {
                        Toast.makeText(ActivityDraw.this, "Поделить невозможно", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception ex){
                    Toast.makeText(ActivityDraw.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setEnabled(true);
                btnReload.setEnabled(false);
                btnNext.setEnabled(false);
                arrayAnimals.clear();
                listNum[0] = 0;
                CountPerson[0] = 0;
                imageAnimals.setImageResource(R.drawable.pic_empty);
                textList.setText("");
                textPersonCount.setText("");
                textTeamCount.setText("");
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    btnNext.setText("Далее");
                    if (listNum[0]<=CountPerson[0]-1){
                        imageAnimals.setImageResource(arrayAnimals.get(listNum[0]));
                        textList.setText((listNum[0]+1)+" из "+CountPerson[0]);
                        listNum[0]++;
                    }
                    else {
                        btnNext.setEnabled(false);
                        btnReload.setEnabled(true);
                        btnNext.setText("Начать");
                    }
                }
                catch(Exception ex){
                    Toast.makeText(ActivityDraw.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

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
                        intent = new Intent(ActivityDraw.this, ActivityUser.class);
                        startActivity(intent);
                        break;
                    case R.id.action_book:
                        intent = new Intent(ActivityDraw.this, ActivityMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.action_toolbox:
                        intent = new Intent(ActivityDraw.this, ActivityTollBox.class);
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