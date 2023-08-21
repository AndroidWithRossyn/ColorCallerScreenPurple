package com.amazingtheme.colorcaller.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.role.RoleManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.amazingtheme.colorcaller.MainActivity;
import com.amazingtheme.colorcaller.R;
import com.amazingtheme.colorcaller.ads.FirebaseADHandlers.AdUtils;
import com.amazingtheme.colorcaller.ads.FirebaseADHandlers.FirebaseUtils;
import com.amazingtheme.colorcaller.ads.PreferencesManager.AppPreferences;
import com.amazingtheme.colorcaller.ads.Utils.Constants;
import com.amazingtheme.colorcaller.ads.Utils.Global;
import com.amazingtheme.colorcaller.callertheme.OS.OtherUntil;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        Drawable background = getResources().getDrawable(R.drawable.status_gradient);
        getWindow().setBackgroundDrawable(background);
        AppPreferences appPreferences = new AppPreferences(this);
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.ADSJSON);
        Constants.adsJsonPOJO = Global.getAdsData(appPreferences.getAdsModel());

        if (Constants.adsJsonPOJO != null && !Constants.isNull(Constants.adsJsonPOJO.getParameters().getApp_open_ad().getDefaultValue().getValue())) {
            Constants.adsJsonPOJO = Global.getAdsData(appPreferences.getAdsModel());
            Constants.hitCounter = Integer.parseInt(Constants.adsJsonPOJO.getParameters().getApp_open_ad().getDefaultValue().getHits());
//            Constants.adsJsonPOJO.getParameters().getShowAd().getDefaultValue().setValue("false");
            nextActivity();
        } else {
            FirebaseUtils.initiateAndStoreFirebaseRemoteConfig(this, adsJsonPOJO -> {
                appPreferences.setAdsModel(adsJsonPOJO);
                Constants.adsJsonPOJO = adsJsonPOJO;
                Constants.hitCounter = Integer.parseInt(Constants.adsJsonPOJO.getParameters().getApp_open_ad().getDefaultValue().getHits());
//                Constants.adsJsonPOJO.getParameters().getShowAd().getDefaultValue().setValue("false");
                nextActivity();
            });
        }


    }
    @Override
    protected void onResume() {
        super.onResume();

        AdUtils.loadInitialInterstitialAds(this);
        AdUtils.loadAppOpenAds(this);
        AdUtils.loadInitialNativeList(this);

    }


    public void nextActivity() {
        AdUtils.showAdIfAvailable(this, state_load -> {
            new Handler().postDelayed(() -> {
//                if (MyApplication.getPreferences().isFirstRun()){
                if (OtherUntil.checkPer(SplashActivity.this)) {
                    finish();
                } else {
                    gotoMainActivity();
                }
           /*     } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }*/

            }, 1600);
        });
    }





    public void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}