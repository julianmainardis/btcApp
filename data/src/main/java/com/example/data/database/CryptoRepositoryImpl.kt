package com.example.data.database

import com.example.data.util.Constants.NOT_FOUND
import com.example.data.util.transformToCryptoEntity
import com.example.data.util.transformToLocalCrypto
import com.example.data.util.transformToLocalCryptoList
import com.example.domain.database.CryptoRepository
import com.example.domain.entity.Crypto
import com.example.domain.util.Result

class CryptoRepositoryImpl(private val cryptoDao: CryptoDao) : CryptoRepository {

    override fun getAllCrypto(): Result<List<Crypto>> =
        cryptoDao.getAllCrypto().let {
            if (it.isNotEmpty()) {
                Result.Success(it.transformToLocalCryptoList())
            } else {
                Result.Failure(Exception(NOT_FOUND))
            }
        }

    override fun updateCrypto(cryptos: List<Crypto>) {
        cryptos.forEach {
            cryptoDao.insertCrypto(it.transformToCryptoEntity())
        }
    }

    override fun getFiatPrice(selection: String): Crypto =
        cryptoDao.getFiatPrice(selection).transformToLocalCrypto()
}
