package com.amazingtheme.colorcaller.koin

import com.amazingtheme.colorcaller.viewmodel.FavViewModel
import com.amazingtheme.colorcaller.viewmodel.ThemesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ThemesViewModel(get())
    }

    viewModel {
        FavViewModel(get())
    }


}
