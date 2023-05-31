package com.example.imtrying.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.imtrying.R;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    private Button button;
    private LinearLayout diceContainer, gifContainer;
    private ImageView img1, img2, gifImg1, gifImg2;

    private final int[] rolls = {
            R.drawable.diceroll1, R.drawable.diceroll2, R.drawable.diceroll3,
            R.drawable.diceroll4, R.drawable.diceroll5, R.drawable.diceroll6
    };

    private final int[] dice = {
            R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6,
    };

    private Boolean isAnimationEnd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        button = findViewById(R.id.btVar1);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        gifContainer = findViewById(R.id.gifContainer);
        gifImg1 = findViewById(R.id.gifImg1);
        gifImg2 = findViewById(R.id.gifImg2);
        diceContainer = findViewById(R.id.diceContainer);

//        Glide.with(DiceActivity.this)
//                .load("https://cdn.discordapp.com/attachments/1112406432432791704/1112435511127179375/dice6.png")
//                .into(img1);
//        Glide.with(DiceActivity.this)
//                .load("https://cdn.discordapp.com/attachments/1112406432432791704/1112435511127179375/dice6.png")
//                .into(img2);

        button.setOnClickListener(v -> {
            try {
                Random random = new Random();
                gifImg1.setVisibility(View.VISIBLE);
                img1.setVisibility(View.GONE);
                gifImg2.setVisibility(View.VISIBLE);
                img2.setVisibility(View.GONE);
                startAnimGif(random.nextInt(6), img1, gifImg1);
                startAnimGif(random.nextInt(6), img2, gifImg2);
            } catch(Exception ex) {
                Toast.makeText(DiceActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startAnimGif(int num, ImageView iv, ImageView gifImg) {
        Log.d(this.getClass().getName(), "Number -> " + num);
        Glide.with(DiceActivity.this).asGif()
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
                                Glide.with(DiceActivity.this).load(dice[num]).into(iv);
                                super.onAnimationEnd(drawable);
                            }
                        });
                        return false;
                    }
                }).into(gifImg);
    }

}