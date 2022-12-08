package com.example.di

import com.example.domain.usecase.GetAllCryptoUseCase
import com.example.domain.usecase.GetAllCryptoUseCaseImpl
import com.example.domain.usecase.GetFiatPriceUseCase
import com.example.domain.usecase.GetFiatPriceUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory<GetAllCryptoUseCase> { GetAllCryptoUseCaseImpl(get(), get()) }
        factory<GetFiatPriceUseCase> { GetFiatPriceUseCaseImpl(get()) }
    }
}
