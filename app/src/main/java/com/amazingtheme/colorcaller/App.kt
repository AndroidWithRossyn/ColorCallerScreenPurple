package com.amazingtheme.colorcaller

import android.app.Application
import com.amazingtheme.colorcaller.ads.FirebaseADHandlers.AppOpenAds
import com.amazingtheme.colorcaller.ads.FirebaseADHandlers.FirebaseUtils
import com.amazingtheme.colorcaller.ads.PreferencesManager.AppPreferences
import com.amazingtheme.colorcaller.ads.Utils.Constants
import com.amazingtheme.colorcaller.ads.Utils.Constants.isNull
import com.amazingtheme.colorcaller.ads.Utils.Global
import com.amazingtheme.colorcaller.koin.getApplicationModules
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import keyboard.neon.newboard.KeyboardInitClass
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


@Suppress("unused")
class App : Application() {
    companion object {
        private lateinit var instance: App

        @Synchronized
        fun getInstance(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()



        instance = this

        val appPreferencesManger = AppPreferences(this)
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.ADSJSON)
        FirebaseApp.initializeApp(this)

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        Constants.adsJsonPOJO = Global.getAdsData(appPreferencesManger.getAdsModel())

        if (Constants.adsJsonPOJO != null && !isNull(Constants.adsJsonPOJO.parameters.app_open_ad.defaultValue.value)) {
            Constants.adsJsonPOJO = Global.getAdsData(appPreferencesManger.getAdsModel())
            Constants.hitCounter = Constants.adsJsonPOJO.parameters.app_open_ad.defaultValue.hits.toInt()
        } else {
            FirebaseUtils.initiateAndStoreFirebaseRemoteConfig(this) { adsJsonPOJO ->
                appPreferencesManger.setAdsModel(adsJsonPOJO)
                Constants.adsJsonPOJO = adsJsonPOJO
                Constants.hitCounter = Constants.adsJsonPOJO.parameters.app_open_ad.defaultValue.hits.toInt()
            }
        }

        MobileAds.initialize(this) { initializationStatus -> }
        AppOpenAds(this)


        KeyboardInitClass(this).onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(Level.ERROR)
            modules(
                getApplicationModules()
            )
        }
    }


}