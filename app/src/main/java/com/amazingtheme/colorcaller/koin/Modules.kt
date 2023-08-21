package com.amazingtheme.colorcaller.koin
import org.koin.core.module.Module


fun getApplicationModules(): List<Module> = listOf(ListModules, viewModelModule)