package com.example.bitcoinapp.di

import com.example.bitcoinapp.viewmodel.AllCryptosViewModel
import com.example.bitcoinapp.viewmodel.FiatPriceViewModel
import com.example.bitcoinapp.viewmodel.MainActivityViewModel
import com.example.bitcoinapp.viewmodel.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel { SplashScreenViewModel() }
        viewModel { MainActivityViewModel() }
        viewModel { AllCryptosViewModel(get()) }
        viewModel { FiatPriceViewModel(get()) }
    }
}
