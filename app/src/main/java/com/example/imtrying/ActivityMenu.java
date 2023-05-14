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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMenu extends AppCompatActivity {

    private ListView listMenu;
    private String[] arrayMenu;
    private ArrayAdapter<String> adapter;
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


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
                try{
                    switch(position){
                        case 0 :

                            break;
                        case 1:
                            Intent intent = new Intent(ActivityMenu.this,ActivityCandles.class);
                            startActivity(intent);
                            break;
                        case 2:

                            break;
                    }
                }
                catch(Exception ex){
                    Toast.makeText(ActivityMenu.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        //
        //-------------------------------------------------------
        //


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
