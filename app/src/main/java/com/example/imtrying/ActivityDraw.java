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

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //
                // Фиксим эту залупу - получение случайных чисел
                //
                if (Integer.valueOf(textCount.getText().toString())!= null){
                    Integer number, count;
                    count = Integer.valueOf(textCount.getText().toString());
                    Integer arrNum[] = new Integer[count];
                    //arrNum.
                    boolean check = true;
                    Random random = new Random();
                    btnStart.setText("Далее");
                // Сразу в зависимости от count заполнить массив в разнобой.
                    while(arrNum[count]==null){
                        while (check){
                            number = random.nextInt(count);
                            if (!Arrays.asList(arrNum).contains(number)){
                                arrNum[arrNum.length-1] = number;
                                check = false;
                                textNum.setText(number.toString());
                            }
                            else {
                                check = true;
                            }
                        }
                    }
                    btnStart.setEnabled(false);
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
            }
        });

    }
}