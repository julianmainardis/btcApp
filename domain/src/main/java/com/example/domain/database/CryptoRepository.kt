package com.example.domain.database

import com.example.domain.entity.Crypto
import com.example.domain.util.Result

interface CryptoRepository {
    fun getAllCrypto(): Result<List<Crypto>>
    fun updateCrypto(cryptos: List<Crypto>)
    fun getFiatPrice(selection: String): Crypto
}
