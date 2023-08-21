package com.amazingtheme.colorcaller.callertheme.categoryui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amazingtheme.colorcaller.MainActivity;
import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.ads.FirebaseADHandlers.AdUtils;
import com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.Theme_GifActivity_Calling_Theme_Preview;
import com.amazingtheme.colorcaller.callertheme.ui.CategoriesActivity;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import pl.droidsonroids.gif.GifImageView;

public class PreviewActivity extends AppCompatActivity {
    TextView btn_set_theam;
    ImageView imageView,callAnswerBtn,callRejectBtn,back1;
    FullScreenVideoView gif_image_view_preview;
    LinearLayout leftArrowContainer, rightArrowContainer,adsView0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btn_set_theam=findViewById(R.id.btn_set_theam);
        adsView0=findViewById(R.id.adsView0);
        AdUtils.showNativeAd(PreviewActivity.this, this.adsView0, false);
        imageView=findViewById(R.id.image_view_preview);
        callAnswerBtn = findViewById(R.id.img_recive);
        callRejectBtn = findViewById(R.id.img_reject);
        back1 = findViewById(R.id.back1);
        gif_image_view_preview = findViewById(R.id.gif_image_view_preview);
        leftArrowContainer = findViewById(R.id.left_arrow_container);
        rightArrowContainer = findViewById(R.id.right_arrow_container);
        ObjectAnimator leftArrow1Animator = ObjectAnimator.ofFloat(leftArrowContainer.getChildAt(0), "translationX", -40f);
        ObjectAnimator leftArrow2Animator = ObjectAnimator.ofFloat(leftArrowContainer.getChildAt(1), "translationX", -40f);

        ObjectAnimator rightArrow1Animator = ObjectAnimator.ofFloat(rightArrowContainer.getChildAt(0), "translationX", 40f);
        ObjectAnimator rightArrow2Animator = ObjectAnimator.ofFloat(rightArrowContainer.getChildAt(1), "translationX", 40f);



        AnimatorSet leftArrowAnimatorSet = new AnimatorSet();
        leftArrowAnimatorSet.playSequentially(
                leftArrow1Animator,
                leftArrow2Animator

        );
        leftArrowAnimatorSet.setDuration(500);
        leftArrowAnimatorSet.setStartDelay(200);

        AnimatorSet rightArrowAnimatorSet = new AnimatorSet();
        rightArrowAnimatorSet.playSequentially(
                rightArrow1Animator,
                rightArrow2Animator

        );
        rightArrowAnimatorSet.setDuration(500);
        rightArrowAnimatorSet.setStartDelay(200);


        callAnswerBtn.setOnTouchListener(new Theme_Activity_Calling_Theme_Preview.SwipeTouchListener(leftArrowContainer, rightArrowContainer, callAnswerBtn));
        callRejectBtn.setOnTouchListener(new Theme_Activity_Calling_Theme_Preview.SwipeTouchListener(leftArrowContainer, rightArrowContainer, callRejectBtn));


        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(PreviewActivity.this, state_load -> {
                    PreviewActivity.super.onBackPressed();
                });

            }
        });
        SharedPreferences sharedPreferences1 = getSharedPreferences("AnotherPrefs", Context.MODE_PRIVATE);
        int receiveIcon = sharedPreferences1.getInt("receiveIcon", R.drawable.accept_button_one);
        int rejectIcon = sharedPreferences1.getInt("rejectIcon", R.drawable.decline_button_one);



        callAnswerBtn.setImageResource(receiveIcon);
        callRejectBtn.setImageResource(rejectIcon);
        SharedPreferences imageSharedPreferences = getSharedPreferences("image_theme", Context.MODE_PRIVATE);
        SharedPreferences gifSharedPreferences = getSharedPreferences("gif_theme", Context.MODE_PRIVATE);

        String imageUrl = imageSharedPreferences.getString("image_url1", null);
        String gifUrl = gifSharedPreferences.getString("image_url1", null);

        if (imageUrl != null && gifUrl != null) {
            long imageTimestamp = imageSharedPreferences.getLong("timestamp", 0);
            long gifTimestamp = gifSharedPreferences.getLong("timestamp", 0);

            if (imageTimestamp > gifTimestamp) {
                imageView.setVisibility(View.VISIBLE);
                gif_image_view_preview.setVisibility(View.GONE);
                Picasso.get().load(imageUrl).into(imageView);
            } else {
                imageView.setVisibility(View.GONE);
                gif_image_view_preview.setVisibility(View.VISIBLE);
                Uri videoUri = Uri.parse(gifUrl);
                gif_image_view_preview.setVideoURI(videoUri);
                gif_image_view_preview.start();
                gif_image_view_preview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setVolume(0f, 0f);
                    }
                });

                gif_image_view_preview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        gif_image_view_preview.setVisibility(View.VISIBLE);
                        gif_image_view_preview.start();
                    }
                });
            }
        } else if (imageUrl != null) {
            imageView.setVisibility(View.VISIBLE);
            gif_image_view_preview.setVisibility(View.GONE);
            Picasso.get().load(imageUrl).into(imageView);
        } else if (gifUrl != null) {
            imageView.setVisibility(View.GONE);
            gif_image_view_preview.setVisibility(View.VISIBLE);
            Uri videoUri = Uri.parse(gifUrl);
            gif_image_view_preview.setVideoURI(videoUri);
            gif_image_view_preview.start();
            gif_image_view_preview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setVolume(0f, 0f);
                }
            });

            gif_image_view_preview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    gif_image_view_preview.setVisibility(View.VISIBLE);
                    gif_image_view_preview.start();
                }
            });
        } else {
            imageView.setVisibility(View.GONE);
            gif_image_view_preview.setVisibility(View.GONE);
        }



        btn_set_theam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(PreviewActivity.this, state_load -> {
                    SharedPreferences anotherActivitySharedPreferences = getSharedPreferences("AnotherActivityPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor anotherActivityEditor = anotherActivitySharedPreferences.edit();
                    anotherActivityEditor.putInt("receiveIcon", receiveIcon);
                    anotherActivityEditor.putInt("rejectIcon", rejectIcon);
                    anotherActivityEditor.apply();
                    Toast.makeText(PreviewActivity.this, "Set Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PreviewActivity.this,MainActivity.class));
                });

            }
        });
    }

    @Override
    public void onBackPressed() {
        AdUtils.showInterstitialAd(PreviewActivity.this, state_load -> {
            PreviewActivity.super.onBackPressed();
        });
    }
}