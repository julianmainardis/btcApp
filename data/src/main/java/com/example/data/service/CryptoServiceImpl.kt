package com.example.data.service

import com.example.data.service.api.CryptoApi
import com.example.data.util.Constants.NOT_FOUND
import com.example.data.util.transformToLocalCryptoList
import com.example.domain.entity.Crypto
import com.example.domain.service.CryptoService
import com.example.domain.util.Result

class CryptoServiceImpl(private val api: ServiceGenerator) : CryptoService {

    override fun getAllCrypto(): Result<List<Crypto>> {
        try {
            val callResponse = api.createService(CryptoApi::class.java).getAllCrypto()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.Success(it.transformToLocalCryptoList())
                }
            }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
        return Result.Failure(Exception(NOT_FOUND))
    }
}
