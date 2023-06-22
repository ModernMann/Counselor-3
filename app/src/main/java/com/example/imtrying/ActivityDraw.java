package com.example.imtrying;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.imtrying.activities.ActivityMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityDraw extends AppCompatActivity {

    Button btnStart, btnReload;
    ImageView imgNext, imgPrev;
    BottomNavigationView bnv;
    public static ImageView imageAnimals;

    EditText textPersonCount, textTeamCount;

    private final int[] rolls = {
            R.drawable.cat, R.drawable.dog, R.drawable.dragon,
            R.drawable.duck, R.drawable.fish, R.drawable.frog, R.drawable.parrot
    };
    TextView textList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        btnStart = findViewById(R.id.btnStart);
        btnReload = findViewById(R.id.btnReload);

        textTeamCount = findViewById(R.id.textTeamCount);
        textPersonCount = findViewById(R.id.textPersonCount);
        imageAnimals = findViewById(R.id.imageAnimals);
        textList = findViewById(R.id.textList);
        imgNext = findViewById(R.id.imageNext);
        imgPrev = findViewById(R.id.imagePrev);
        btnStart.setEnabled(true);
        imgNext.setEnabled(false);
        imgPrev.setEnabled(false);
        btnReload.setEnabled(false);
        List<Integer> arrayAnimals = new ArrayList<>();
        final Integer[] CountPerson = new Integer[1];
        final Integer[] listNum = {0};




        btnStart.setOnClickListener(v -> {
            try{

                Integer personCount = Integer.parseInt(textPersonCount.getText().toString());
                CountPerson[0] = personCount;
                Integer teamCount = Integer.parseInt(textTeamCount.getText().toString());
                if (personCount%teamCount==0){

                    for (int j=0;j<teamCount;j++){
                        for (int i=0;i<personCount/teamCount;i++){
                            arrayAnimals.add(rolls[j]);
                        }
                    }
                    Collections.shuffle(arrayAnimals);
                    imgNext.setEnabled(true);
                    imgPrev.setEnabled(false);
                    btnReload.setEnabled(true);
                }
                else {
                    Toast.makeText(ActivityDraw.this, "Поделить невозможно", Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception ex){
                Toast.makeText(ActivityDraw.this, ex.toString(), Toast.LENGTH_SHORT).show();
            }

        });

        btnReload.setOnClickListener(v -> {
            btnStart.setEnabled(true);
            btnReload.setEnabled(false);
            imgNext.setEnabled(false);
            imgPrev.setEnabled(false);
            arrayAnimals.clear();
            listNum[0] = 0;
            CountPerson[0] = 0;
            imageAnimals.setImageResource(R.color.lavender);
            textList.setText("");
            textPersonCount.setText("");
            textTeamCount.setText("");
        });

        imgNext.setOnClickListener(v -> {
            if (listNum[0]<=CountPerson[0]){
                startAnimGif(listNum[0], imageAnimals, imageAnimals);
                textList.setText((listNum[0]+1)+" из "+CountPerson[0]);
                listNum[0]++;
            }
            else {
                imgNext.setEnabled(false);
            }
        });
        imgPrev.setOnClickListener(v -> {
            if (listNum[0]>=0){
                startAnimGif(listNum[0], imageAnimals, imageAnimals);
                textList.setText((listNum[0]+1)+" из "+CountPerson[0]);
                listNum[0]--;
            }
            else {
                imgPrev.setEnabled(false);
            }
        });








        //
        // Нижнее меню навигации и его действия
        //
        bnv = findViewById(R.id.bottomNavigationView5);
        bnv.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Intent intent;
            switch(id){
                case R.id.action_user:
                    intent = new Intent(ActivityDraw.this, ActivityUser.class);
                    startActivity(intent);
                    break;
                case R.id.action_toolbox:
                    intent = new Intent(ActivityDraw.this, ActivityTollBox.class);
                    startActivity(intent);
                    break;

            }
            return true;
        });
        //
        //------------------------------------------------------------------------------

    }
    private void startAnimGif(int num, ImageView iv, ImageView gifImg) {
        Log.d(this.getClass().getName(), "Number -> " + num);
        Glide.with(ActivityDraw.this).asGif()
                .load(rolls[num])
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        resource.setLoopCount(1);
                        resource.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                            @Override
                            public void onAnimationEnd(Drawable drawable) {
                                iv.setVisibility(View.VISIBLE);
                                gifImg.setVisibility(View.GONE);
                                Glide.with(ActivityDraw.this).load(rolls[num]).into(iv);
                                super.onAnimationEnd(drawable);
                            }
                        });
                        return false;
                    }
                }).into(gifImg);
    }
}