package com.example.imtrying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listMenu;
    private String[] arrayMenu;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        //Intent intent = new Intent(this, ActivityGames.class);
        //startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}