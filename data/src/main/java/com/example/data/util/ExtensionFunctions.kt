package com.example.data.util

import com.example.data.entity.CryptoEntity
import com.example.data.service.response.ResultResponse
import com.example.domain.entity.Crypto

fun ResultResponse.transformToLocalCryptoList(): List<Crypto> {
    val cryptoList = mutableListOf<Crypto>()
    results.forEach {
        cryptoList.add(
            Crypto(
                it.currency,
                it.actualValue
            )
        )
    }
    return cryptoList
}

fun CryptoEntity.transformToLocalCrypto() =
    Crypto(
        this.currency,
        this.actualValue
    )

fun Crypto.transformToCryptoEntity() =
    CryptoEntity(
        this.currency,
        this.actualValue
    )

fun List<CryptoEntity>.transformToLocalCryptoList(): List<Crypto> =
    this.map { it.transformToLocalCrypto() }
