package com.example.di

import com.example.data.database.CryptoRepositoryImpl
import com.example.domain.database.CryptoRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        factory<CryptoRepository> { CryptoRepositoryImpl(get()) }
    }
}
