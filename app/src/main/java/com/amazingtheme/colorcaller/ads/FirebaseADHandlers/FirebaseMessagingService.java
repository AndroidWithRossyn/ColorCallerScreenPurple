package com.amazingtheme.colorcaller.ads.FirebaseADHandlers;

import androidx.annotation.NonNull;

import com.amazingtheme.colorcaller.ads.PreferencesManager.AppPreferences;
import com.amazingtheme.colorcaller.ads.Utils.Constants;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    public FirebaseMessagingService() {
        super();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (!remoteMessage.getData().isEmpty()) {
            if (remoteMessage.getData().containsValue("10")) {
                FirebaseUtils.initiateAndStoreFirebaseRemoteConfig(getApplicationContext(), adsJsonPOJO -> {
                    AppPreferences appPreferencesManger = new AppPreferences(getApplicationContext());
                    appPreferencesManger.setAdsModel(adsJsonPOJO);
                    Constants.adsJsonPOJO = adsJsonPOJO;
                });
            }
        }

    }
}
