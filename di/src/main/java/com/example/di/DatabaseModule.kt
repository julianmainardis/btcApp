package com.example.di

import androidx.room.Room
import com.example.data.database.CryptoDatabase
import org.koin.dsl.module

object DatabaseModule {
    private const val DATA_BASE_NAME = "CryptoRepository"

    val dataBaseModule = module {
        single { Room.databaseBuilder(get(), CryptoDatabase::class.java, DATA_BASE_NAME).build() }
        single { get<CryptoDatabase>().cryptoDao() }
    }
}
