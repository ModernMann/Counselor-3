package com.example.imtrying;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class ActivityDraw extends AppCompatActivity {

    Button btnStart, btnReload;
    TextView textNum;
    EditText textCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        btnStart = findViewById(R.id.btnStart);
        btnReload = findViewById(R.id.btnReload);
        textCount = findViewById(R.id.textCount);
        textNum = findViewById(R.id.textNumber);
        Integer i = 0;
        btnStart.setEnabled(true);
        btnReload.setEnabled(false);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //
                // Фиксим эту залупу - получение случайных чисел
                //
                if (Integer.valueOf(textCount.getText().toString())!= null){
                    btnReload.setEnabled(true);
                    btnStart.setText("Далее");
                    Integer count;
                    Integer[] arrayOfPlayers = new Integer[10];
                    final int dice[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
                }
                //
                // До сюда
                //
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCount.setText("");
                btnStart.setEnabled(true);
                btnReload.setEnabled(false);
            }
        });

    }
}