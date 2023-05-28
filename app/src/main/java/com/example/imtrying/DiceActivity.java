package com.example.imtrying;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Random;

import pl.droidsonroids.gif.AnimationListener;


public class DiceActivity extends AppCompatActivity {

    public static Button button;
    public static ImageView img1, img2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        final int dice[] = {R.drawable.diceroll1, R.drawable.diceroll3, R.drawable.diceroll5,
                R.drawable.diceroll6};

        button = findViewById(R.id.btVar1);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Random random = new Random();
                    int num1 = random.nextInt(4);
                    Random random1 = new Random();
                    int num2 = random.nextInt(4);

                    img1.setImageResource(R.drawable.diceroll3);
                    img2.setImageResource(R.drawable.diceroll3);

                    //img1.setImageResource(dice[num1]);

                    //img2.setImageResource(dice[num2]);
                }
                catch(Exception ex){
                    Toast.makeText(DiceActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}