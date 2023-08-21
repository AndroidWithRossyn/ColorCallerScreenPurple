package com.amazingtheme.colorcaller.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amazingtheme.colorcaller.EnableKeyboard
import com.amazingtheme.colorcaller.MainActivity
import com.amazingtheme.colorcaller.R
import com.amazingtheme.colorcaller.RatingDialog
import com.amazingtheme.colorcaller.apputils.privacyPolicy
import com.amazingtheme.colorcaller.apputils.sendEmail
import com.amazingtheme.colorcaller.apputils.shareApp
import com.amazingtheme.colorcaller.databinding.FragmentMainSettingsBinding
import com.amazingtheme.colorcaller.keyboardutils.isVisible
import keyboard.neon.newboard.util.checkIfImeIsSelected

class SettingsMainFragment : BaseFragment() {

    private lateinit var myActivity: MainActivity
    private lateinit var binding: FragmentMainSettingsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myActivity = context as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainSettingsBinding.inflate(inflater, container, false)


        // load pref settings fragment
        loadChildFragment(NeonKeyboardSettingsFragment(), binding.settingsPrefContainer.id)


        with(binding)
        {
            enableDisableKbSetting.setOnClickListener {
                startActivity(Intent(myActivity, EnableKeyboard::class.java))
            }
            shareSettings.setOnClickListener {

                myActivity.shareApp()
            }
            rateUsSettings.setOnClickListener {


                val dialog = RatingDialog()
                dialog.show(myActivity.supportFragmentManager, "ratingDialog")


//                RatingDialog(myActivity).createAndShowRatingDialog {
//
//
//                    if (it)
//                        rateUs(myActivity)
//                    else
//                        myActivity.showToast("Thanks for rating us.")
//
//                }

            }

            feedBackSetting.setOnClickListener {

                myActivity.sendEmail()
            }

            privacySettings.setOnClickListener {

                myActivity.privacyPolicy()
            }
        }


        return binding.root
    }



    override fun onResume() {
        super.onResume()
        if (checkIfImeIsSelected(myActivity))
            binding.appCompatImageView.setImageResource(R.drawable.ic_settings_enabled_keyboard)
        else
            binding.appCompatImageView.setImageResource(R.drawable.ic_setting_enabledisable)
    }
}