package com.example.imtrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMenu extends AppCompatActivity {

    private ListView listMenu;
    private String[] arrayMenu;
    private ArrayAdapter<String> adapter;
    BottomNavigationView bnv;
    Button btnGame, btnCandle, btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnGame = findViewById(R.id.btnGame);
        btnCandle = findViewById(R.id.btnCandle);
        btnBook = findViewById(R.id.btnBook);

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // позже
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
