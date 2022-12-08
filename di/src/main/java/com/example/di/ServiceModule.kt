package com.example.di

import com.example.data.service.CryptoServiceImpl
import com.example.domain.service.CryptoService
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        factory<CryptoService> { CryptoServiceImpl(get()) }
    }
}
