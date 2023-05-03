package com.example.imtrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.imtrying.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

    private ListView listUser;
    private ArrayAdapter<String> adapter;
    private List<String> listData;

    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        init();
        getDataFromDB();
    }
    private void init(){
        listUser = findViewById(R.id.listUser);
        listData = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listUser.setAdapter(adapter);
        users = FirebaseDatabase.getInstance().getReference("Users");
    }
    private void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Если лист не пустой - чистим
                if (listData.size()>0){
                    listData.clear();
                }
                // Вытягиваеи данные из БД
                for(DataSnapshot ds : snapshot.getChildren()){

                    User user = ds.getValue(User.class);
                    //проверяем какой юзер приходит - пустой или нет
                    assert user != null;
                    listData.add(user.getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        users.addValueEventListener(vListener);
    }
}