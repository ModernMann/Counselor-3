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


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (Integer.valueOf(textCount.getText().toString())!= null){
                    Integer number, count, i = 0;
                    count = Integer.valueOf(textCount.getText().toString());
                    Integer arrNum[] = new Integer[count];
                    boolean check = true;
                    Random random = new Random();

                    number = random.nextInt(count);
                    while (check){
                        if (!Arrays.asList(arrNum).contains(number)){
                            arrNum[i] = number;
                            i++;
                            check = false;
                            textNum.setText(number.toString());
                        }
                        else {

                        }
                    }

                }
            }
        });

    }
}