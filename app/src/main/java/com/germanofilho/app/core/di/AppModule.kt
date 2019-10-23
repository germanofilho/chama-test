package com.germanofilho.app.core.di

import com.germanofilho.app.feature.home.presentation.viewmodel.HomeViewModel
import com.germanofilho.app.feature.home.repository.HomeRepository
import com.germanofilho.app.feature.home.repository.IHomeRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //Home DI
    viewModel {
        HomeViewModel(get())
    }

    single {
        HomeRepository() as IHomeRepository
    }
}