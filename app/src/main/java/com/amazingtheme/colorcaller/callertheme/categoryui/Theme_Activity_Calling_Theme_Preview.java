package com.amazingtheme.colorcaller.callertheme.categoryui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceManager;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amazingtheme.colorcaller.MainActivity;
import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.ads.FirebaseADHandlers.AdUtils;
import com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.kpopCategory.AvtarAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import pl.droidsonroids.gif.GifImageView;

public class Theme_Activity_Calling_Theme_Preview extends AppCompatActivity {
    ImageView imageView, favourites;
    GifImageView gifimageView;
    TextView btn_set_theam;
    RelativeLayout customize_call_rl, avatar_rl, font_rl, buttons_rl;
    private GridView gridView;
    private ArrayList<DataClass> dataList;
    private AvtarAdapter adapter;
    View view1, view2, view3;
    ImageView img_recive, img_reject, back1;
    private String selectedImageUrl;

    LinearLayout leftArrowContainer, rightArrowContainer,adsView0;
    public static final String FAVORITES_PREF_NAME = "my_favorites_theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_calling_theme_preview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageView = findViewById(R.id.image_view_preview);
        btn_set_theam = findViewById(R.id.btn_set_theam);
        avatar_rl = findViewById(R.id.avatar_rl);
        back1 = findViewById(R.id.back1);
        adsView0=findViewById(R.id.adsView0);
        AdUtils.showNativeAd(Theme_Activity_Calling_Theme_Preview.this, this.adsView0, false);
        font_rl = findViewById(R.id.font_rl);
        buttons_rl = findViewById(R.id.buttons_rl);
        buttons_rl = findViewById(R.id.buttons_rl);
        favourites = findViewById(R.id.favourites);
        img_recive = findViewById(R.id.img_recive);
        img_reject = findViewById(R.id.img_reject);
        customize_call_rl = findViewById(R.id.customize_call_rl);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        avatar_rl.setVisibility(View.VISIBLE);
        view1.setVisibility(View.VISIBLE);
        gridView = findViewById(R.id.avatar_rec);
        dataList = new ArrayList<>();
        gridView.setVisibility(View.VISIBLE);
        adapter = new AvtarAdapter(this, dataList);
        gridView.setAdapter(adapter);
        customize_call_rl.setVisibility(View.GONE);


        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdUtils.showInterstitialAd(Theme_Activity_Calling_Theme_Preview.this, state_load -> {
                    Theme_Activity_Calling_Theme_Preview.super.onBackPressed();
                });

            }
        });
        String imageUrl = getIntent().getStringExtra("image_url");
        Picasso.get().load(imageUrl).into(imageView);
        SharedPreferences sharedPreferences = getSharedPreferences(FAVORITES_PREF_NAME, Context.MODE_PRIVATE);
        Set<String> favoriteUrls = sharedPreferences.getStringSet("favorite_urls", new HashSet<>());

        // Check if the current image URL is in the list of favorite URLs
        if (imageUrl != null && favoriteUrls.contains(imageUrl)) {
            // Set the color of the favorite button to red
            favourites.setColorFilter(ContextCompat.getColor(Theme_Activity_Calling_Theme_Preview.this, R.color.red));
        }
        leftArrowContainer = findViewById(R.id.left_arrow_container);
        rightArrowContainer = findViewById(R.id.right_arrow_container);

// Create the animations for the left arrows
        ObjectAnimator leftArrow1Animator = ObjectAnimator.ofFloat(leftArrowContainer.getChildAt(0), "translationX", -40f);
        ObjectAnimator leftArrow2Animator = ObjectAnimator.ofFloat(leftArrowContainer.getChildAt(1), "translationX", -40f);


// Create the animations for the right arrows
        ObjectAnimator rightArrow1Animator = ObjectAnimator.ofFloat(rightArrowContainer.getChildAt(0), "translationX", 40f);
        ObjectAnimator rightArrow2Animator = ObjectAnimator.ofFloat(rightArrowContainer.getChildAt(1), "translationX", 40f);


        AnimatorSet leftArrowAnimatorSet = new AnimatorSet();
        leftArrowAnimatorSet.playSequentially(leftArrow1Animator, leftArrow2Animator

        );
        leftArrowAnimatorSet.setDuration(500);
        leftArrowAnimatorSet.setStartDelay(200); // Adjust the start delay as needed

// Create an AnimatorSet for the right arrows
        AnimatorSet rightArrowAnimatorSet = new AnimatorSet();
        rightArrowAnimatorSet.playSequentially(rightArrow1Animator, rightArrow2Animator

        );
        rightArrowAnimatorSet.setDuration(500);
        rightArrowAnimatorSet.setStartDelay(200);


        img_recive.setOnTouchListener(new SwipeTouchListener(leftArrowContainer, rightArrowContainer, img_recive));
        img_reject.setOnTouchListener(new SwipeTouchListener(leftArrowContainer, rightArrowContainer, img_reject));


       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataClass dataClass = dataSnapshot.getValue(DataClass.class);
                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/
        /*customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customize_call_rl.setVisibility(View.VISIBLE);
            }
        });*/
        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFavoritesIfImageSet(imageUrl);
            }
        });

        btn_set_theam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("image_theme", Context.MODE_PRIVATE);

                if (imageUrl != null) {
                    // Perform the necessary actions with the downloaded image URL
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("image_url1", imageUrl);
                    editor.putLong("timestamp", System.currentTimeMillis());
                    editor.apply();

                    selectedImageUrl = imageUrl;
                    Toast.makeText(Theme_Activity_Calling_Theme_Preview.this, "Applied Successfully", Toast.LENGTH_SHORT).show();
//                    // Add to favorites only if the image is set successfully
//                    addToFavoritesIfImageSet(imageUrl);
                } else {
                    Toast.makeText(Theme_Activity_Calling_Theme_Preview.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //Avatar Recyclerview code...

        font_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridView.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.GONE);

            }
        });

    }


    private void addToFavoritesIfImageSet(String imageUrl) {
        SharedPreferences sharedPreferences = getSharedPreferences(FAVORITES_PREF_NAME, Context.MODE_PRIVATE);
        Set<String> favoriteUrls = sharedPreferences.getStringSet("favorite_urls", new HashSet<>());

        if (imageUrl != null) {
            if (favoriteUrls.contains(imageUrl)) {
                // Item is already marked as favorite, remove it from favorites
                favoriteUrls.remove(imageUrl);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet("favorite_urls", favoriteUrls);
                editor.apply();
                favourites.setColorFilter(null); // Reset color to default
                Toast.makeText(Theme_Activity_Calling_Theme_Preview.this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
            } else {
                // Item is not marked as favorite, add it to the favorites list
                favoriteUrls.add(imageUrl);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet("favorite_urls", favoriteUrls);
                editor.apply();
                favourites.setColorFilter(ContextCompat.getColor(Theme_Activity_Calling_Theme_Preview.this, R.color.red));
                Toast.makeText(Theme_Activity_Calling_Theme_Preview.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        }
    }

/*    private void addToFavoritesIfImageSet(String imageUrl) {
        SharedPreferences sharedPreferences = getSharedPreferences(FAVORITES_PREF_NAME, Context.MODE_PRIVATE);
        Set<String> favoriteUrls = sharedPreferences.getStringSet("favorite_urls", new HashSet<>());

        if (imageUrl != null) {
            if (favoriteUrls.contains(imageUrl)) {
                // Item is already marked as favorite
                Toast.makeText(Theme_Activity_Calling_Theme_Preview.this, "Already in Favorites", Toast.LENGTH_SHORT).show();
            } else {
                // Item is not marked as favorite, add it to the favorites list
                favoriteUrls.add(imageUrl);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet("favorite_urls", favoriteUrls);
                editor.apply();
                favourites.setColorFilter(ContextCompat.getColor(Theme_Activity_Calling_Theme_Preview.this, R.color.red));

                Toast.makeText(Theme_Activity_Calling_Theme_Preview.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        }
    }*/


    static class SwipeTouchListener implements View.OnTouchListener {
        private LinearLayout leftArrowContainer;
        private LinearLayout rightArrowContainer;
        private ImageView leftArrow1;
        private ImageView leftArrow2;

        private ImageView rightArrow1;
        private ImageView rightArrow2;

        private boolean isSwiping = false;
        private float initialX;
        private ImageView buttonView;
        private ObjectAnimator fadeOutAnimator;
        private ObjectAnimator fadeInAnimator;

        SwipeTouchListener(LinearLayout leftArrowContainer, LinearLayout rightArrowContainer, ImageView buttonView) {
            this.leftArrowContainer = leftArrowContainer;
            this.rightArrowContainer = rightArrowContainer;
            this.buttonView = buttonView;

            leftArrow1 = leftArrowContainer.findViewById(R.id.left_arrow_1);
            leftArrow2 = leftArrowContainer.findViewById(R.id.left_arrow_2);
            rightArrow1 = rightArrowContainer.findViewById(R.id.right_arrow_1);
            rightArrow2 = rightArrowContainer.findViewById(R.id.right_arrow_2);

            fadeOutAnimator = ObjectAnimator.ofFloat(buttonView, "alpha", 1f, 0.5f);
            fadeOutAnimator.setDuration(200);
            fadeOutAnimator.setInterpolator(new LinearInterpolator());

            fadeInAnimator = ObjectAnimator.ofFloat(buttonView, "alpha", 0.5f, 1f);
            fadeInAnimator.setDuration(200);
            fadeInAnimator.setInterpolator(new LinearInterpolator());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isSwiping = false;
                    initialX = event.getX();
                    showArrows();
                    fadeOutAnimator.cancel();
                    fadeInAnimator.cancel();
                    buttonView.setAlpha(1f);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    if (!isSwiping && Math.abs(event.getX() - initialX) > 100) {
                        isSwiping = true;
                    }
                    if (isSwiping) {
                        buttonView.setTranslationX(event.getX() - initialX);
                        fadeOutAnimator.start();
                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    hideArrows();
                    if (isSwiping) {
                        float deltaX = event.getX() - initialX;
                        if (deltaX > 0) {
                            // Swiped right
                            // Handle receive call action here
                        } else {
                            // Swiped left
                            // Handle end call action here
                        }
                    }
                    buttonView.setTranslationX(0f);
                    fadeInAnimator.start();
                    return true;
            }
            return false;
        }

        private void showArrows() {
            leftArrow1.setVisibility(View.VISIBLE);
            leftArrow1.setTranslationX(-50);
            leftArrow1.setAlpha(0f);
            leftArrow1.animate().translationXBy(50).alpha(1f).setDuration(200).start();

            leftArrow2.setVisibility(View.VISIBLE);
            leftArrow2.setTranslationX(-50);
            leftArrow2.setAlpha(0f);
            leftArrow2.animate().translationXBy(50).alpha(1f).setStartDelay(100).setDuration(200).start();


            rightArrow1.setVisibility(View.VISIBLE);
            rightArrow1.setTranslationX(50);
            rightArrow1.setAlpha(0f);
            rightArrow1.animate().translationXBy(-50).alpha(1f).setDuration(200).start();

            rightArrow2.setVisibility(View.VISIBLE);
            rightArrow2.setTranslationX(50);
            rightArrow2.setAlpha(0f);
            rightArrow2.animate().translationXBy(-50).alpha(1f).setStartDelay(100).setDuration(200).start();


        }


        private void hideArrows() {
            leftArrow1.animate().translationXBy(50).alpha(0f).setDuration(200).start();
            leftArrow2.animate().translationXBy(50).alpha(0f).setDuration(200).start();
            rightArrow1.animate().translationXBy(-50).alpha(0f).setDuration(200).start();
            rightArrow2.animate().translationXBy(-50).alpha(0f).setDuration(200).start();
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Theme_Activity_Calling_Theme_Preview.this, MainActivity.class));
    }
}
