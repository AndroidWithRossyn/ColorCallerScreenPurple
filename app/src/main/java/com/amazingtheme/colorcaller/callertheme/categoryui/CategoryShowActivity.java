package com.amazingtheme.colorcaller.callertheme.categoryui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.kpopCategory.ViewPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CategoryShowActivity extends AppCompatActivity {
    private static final String TAG = "CategoryShowActivity";
    private ArrayList<Images> lstImages = new ArrayList<>();
    ImageView back;
    private int clickedPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_show);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ViewPager viewPager = findViewById(R.id.view_pager);
        back = findViewById(R.id.back);
        ArrayList<Parcelable> parcelableArrayList = getIntent().getParcelableArrayListExtra("imageUrl");
        if (parcelableArrayList != null) {
            for (Parcelable parcelable : parcelableArrayList) {
                if (parcelable instanceof Images) {
                    Images image = (Images) parcelable;
                    lstImages.add(image);
                }
            }
        }
        clickedPosition = getIntent().getIntExtra("position", 0);
        Log.println(Log.ASSERT, TAG, "lstImages: " + lstImages.size());


        ViewPagerAdapter adapter = new ViewPagerAdapter(this, lstImages);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(clickedPosition);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryShowActivity.super.onBackPressed();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}