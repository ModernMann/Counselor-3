package com.example.imtrying.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.imtrying.Models.User;
import com.example.imtrying.R;
import com.example.imtrying.databinding.FragmentDiceBinding;

import java.util.Random;

public class DiceFragment extends Fragment {

    private FragmentDiceBinding binding;

    private final int[] rolls = {
            R.drawable.diceroll1, R.drawable.diceroll2, R.drawable.diceroll3,
            R.drawable.diceroll4, R.drawable.diceroll5, R.drawable.diceroll6
    };

    private final int[] dice = {
            R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6,
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDiceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        User user = (User) getArguments().getSerializable("user");
        assert user != null;

        binding.include.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        binding.include.toolbar.setNavigationOnClickListener(v -> navigateTo(user, new ToolBoxFragment()));
        binding.btVar1.setOnClickListener(v -> {
            try {
                Random random = new Random();
                binding.gifImg1.setVisibility(View.VISIBLE);
                binding.img1.setVisibility(View.GONE);
                binding.gifImg2.setVisibility(View.VISIBLE);
                binding.img2.setVisibility(View.GONE);
                startAnimGif(random.nextInt(6), binding.img1, binding.gifImg1, getContext());
                startAnimGif(random.nextInt(6), binding.img2, binding.gifImg2, getContext());
            } catch(Exception ex) {
                Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startAnimGif(int num, ImageView iv, ImageView gifImg, Context context) {
        Glide.with(context).asGif()
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
                                Glide.with(context).load(dice[num]).into(iv);
                                super.onAnimationEnd(drawable);
                            }
                        });
                        return false;
                    }
                }).into(gifImg);
    }

    private void navigateTo(User user, Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        fragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.bottomNavHostFragment, fragment)
                .commit();
    }

}