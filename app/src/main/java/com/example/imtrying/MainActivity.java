package com.example.imtrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ListView listMenu;
    private String[] arrayMenu;
    private ArrayAdapter<String> adapter;
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //
        // Навигация в списке
        //
        listMenu = findViewById(R.id.listMenu);
        arrayMenu = getResources().getStringArray(R.array.Menu);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayMenu);
        listMenu.setAdapter(adapter);

        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 switch(position){
                     case 0 :
                         intent = new Intent(MainActivity.this, ActivityGames.class);
                         startActivity(intent);
                         break;
                     case 1:
                         //intent = new Intent(MainActivity.this, ActivityGames.class);
                         //startActivity(intent);
                         break;
                     case 2:
                         //intent = new Intent(MainActivity.this, ActivityGames.class);
                         //startActivity(intent);
                         break;
                 }
            }
        });
        //
        //-------------------------------------------------------
        //


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
                        intent = new Intent(MainActivity.this, ActivityUser.class);
                        startActivity(intent);
                        break;
                    case R.id.action_book:
                        break;
                    case R.id.action_toolbox:
                        intent = new Intent(MainActivity.this, ActivityTollBox.class);
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