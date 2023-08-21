package com.amazingtheme.colorcaller.koin


import com.amazingtheme.colorcaller.db.ThemeRepo
import org.koin.dsl.module

val ListModules = module {


    single {
        ThemeRepo(get())
    }

    single {

//        AdsManager(get())
    }


}

