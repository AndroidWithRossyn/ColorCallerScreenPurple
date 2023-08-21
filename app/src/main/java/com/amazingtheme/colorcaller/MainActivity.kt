package com.amazingtheme.colorcaller

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.amazingtheme.colorcaller.ads.FirebaseADHandlers.AdUtils
import com.amazingtheme.colorcaller.callertheme.FavouriteActivity
import com.amazingtheme.colorcaller.callertheme.dialer.DialerActivity
import com.amazingtheme.colorcaller.databinding.ActivityMainBinding
import com.amazingtheme.colorcaller.fragments.HomeFragment
import com.amazingtheme.colorcaller.fragments.IconFragment
import com.amazingtheme.colorcaller.fragments.SettingsMainFragment
import com.amazingtheme.colorcaller.fragments.ThemeOptionsFragment


class MainActivity : BaseActivity() {
    companion object {
        private const val SELECTED_ITEM_ID = "SELECTED_ITEM_ID"
        val DEFAULT_DIALER_REQUEST_ID = 83
        val TAG = "MADARA"
    }

    private val mainActivityBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivityBinding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        val background: Drawable = resources.getDrawable(R.drawable.status_gradient)
        window.setBackgroundDrawable(background)

        with(mainActivityBinding) {
            bottomNavigationView.itemIconTintList = null
            bottomNavigationView.setOnItemSelectedListener { menuItem ->
//                val executorService: ExecutorService = Executors.newSingleThreadExecutor()
//                executorService.execute {
                    when (menuItem.itemId) {
                        R.id.themesNav ->

                            loadFragment(HomeFragment())

                        R.id.keyboardNav ->

                            loadFragment(ThemeOptionsFragment())


                        R.id.iconsNav ->

                            loadFragment(IconFragment())


                        R.id.settingsNav ->

                            loadFragment(SettingsMainFragment())


                    }
//                    executorService.shutdown()
//                }
                true
            }
        }

        mainActivityBinding.menuBtn.setOnClickListener {
            mainActivityBinding.drawer.openDrawer(Gravity.LEFT)
        }
        mainActivityBinding.callerScreenLl.setOnClickListener {
            mainActivityBinding.drawer.closeDrawer(Gravity.LEFT)
            loadFragmentWithIcon(ThemeOptionsFragment(), R.drawable.home_clear)
        }

        mainActivityBinding.keyboardLl.setOnClickListener {
            mainActivityBinding.drawer.closeDrawer(Gravity.LEFT)
            loadFragmentWithIcon(ThemeOptionsFragment(), R.drawable.keyboard_clear)

        }

        mainActivityBinding.iconLl.setOnClickListener {
            mainActivityBinding.drawer.closeDrawer(Gravity.LEFT)
            loadFragmentWithIcon(IconFragment(), R.drawable.icons_clear)

        }
        mainActivityBinding.settingLl.setOnClickListener {
            mainActivityBinding.drawer.closeDrawer(Gravity.LEFT)
            loadFragmentWithIcon(SettingsMainFragment(), R.drawable.setting_clear)

        }


        mainActivityBinding.shareap.setOnClickListener {
            shareApp()
            mainActivityBinding.drawer.closeDrawer(Gravity.LEFT)
        }
        mainActivityBinding.settingLl.setOnClickListener {
            loadFragment(SettingsMainFragment())
            mainActivityBinding.drawer.closeDrawer(Gravity.LEFT)
        }
        mainActivityBinding.rateLl.setOnClickListener {
            val appPackageName = "com.amazingtheme.colorcaller"
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (e: android.content.ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }

        mainActivityBinding.bottomNavigationView.selectedItemId =
            savedInstanceState?.getInt(SELECTED_ITEM_ID) ?: R.id.themesNav

//        val executor: ExecutorService = Executors.newSingleThreadExecutor()
//        executor.submit {
        mainActivityBinding.dialerBtn.setOnClickListener {
            AdUtils.showInterstitialAd(this@MainActivity) {
                startActivity(Intent(this@MainActivity, DialerActivity::class.java))
            }
        }
//        }
//        executor.shutdown()

//        val executor1: ExecutorService = Executors.newSingleThreadExecutor()
//        executor1.submit {
        mainActivityBinding.favouriteBtn.setOnClickListener {
            AdUtils.showInterstitialAd(this@MainActivity) {
                startActivity(Intent(this@MainActivity, FavouriteActivity::class.java))
            }
//            }

        }
//        executor1.shutdown()


        mainActivityBinding.bottomNavigationView.setOnItemReselectedListener {
            if (it.itemId != mainActivityBinding.bottomNavigationView.selectedItemId) {
                mainActivityBinding.bottomNavigationView.selectedItemId = it.itemId
            }
        }
    }
    private fun loadFragmentWithIcon(fragment: Fragment, iconResourceId: Int) {
        val bundle = Bundle()
        bundle.putInt("iconResourceId", iconResourceId)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onBackPressed() {
        if (intent.getBooleanExtra("fromKeyboard2", false)) {
            finishAffinity()
        } else {
            if (mainActivityBinding.bottomNavigationView.selectedItemId == R.id.themesNav)
                showExitBottomSheet()
            else
                mainActivityBinding.bottomNavigationView.selectedItemId = R.id.themesNav
        }
    }

    private fun showExitBottomSheet() {
        supportFragmentManager.let {
            ExitButtonDialog.newInstance().apply {
                setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomBottomSheetDialogTheme)
                show(it, tag)
            }
        }
    }

    private fun Context.shareApp() {
        startActivity(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Use this Neon keyboard with new and trending themes \n and enjoy new keyboard look and feel \n https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
            )
            type = "text/plain"
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_ITEM_ID, mainActivityBinding.bottomNavigationView.selectedItemId)
    }

    override fun onResume() {
        super.onResume()
        AdUtils.loadInitialInterstitialAds(this@MainActivity)
        AdUtils.loadAppOpenAds(this@MainActivity)
        AdUtils.loadInitialNativeList(this)
    }
}
