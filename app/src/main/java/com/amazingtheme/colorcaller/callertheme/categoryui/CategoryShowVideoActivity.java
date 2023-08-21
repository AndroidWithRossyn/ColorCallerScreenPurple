package com.amazingtheme.colorcaller.callertheme.categoryui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.kpopCategory.ViewPager1Adapter;
import com.amazingtheme.colorcaller.callertheme.categoryui.linearCategory.kpopCategory.ViewPagerAdapter;

import java.util.ArrayList;

public class CategoryShowVideoActivity extends AppCompatActivity {
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
        // Retrieve the arrayList from the Intent
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


        ViewPager1Adapter adapter = new ViewPager1Adapter(this, lstImages);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(clickedPosition);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryShowVideoActivity.super.onBackPressed();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}