package com.example.domain.usecase

import com.example.domain.database.CryptoRepository
import com.example.domain.entity.Crypto

interface GetFiatPriceUseCase {
    operator fun invoke(selected: String): Crypto
}

class GetFiatPriceUseCaseImpl(
    private val database: CryptoRepository
) : GetFiatPriceUseCase {

    override fun invoke(selected: String): Crypto = database.getFiatPrice(selection = selected)
}
