package com.amazingtheme.colorcaller.viewmodel

import androidx.lifecycle.ViewModel
import com.amazingtheme.colorcaller.db.ThemeRepo
import com.amazingtheme.colorcaller.models.ThemesModel

class FavViewModel(var themesRepo: ThemeRepo) : ViewModel() {


    fun adThemeToFav(item: ThemesModel, favStatus: (Boolean) -> Unit) {

        themesRepo.addToFav(item, favStatus = { status ->
            favStatus.invoke(status)

        })


    }




}