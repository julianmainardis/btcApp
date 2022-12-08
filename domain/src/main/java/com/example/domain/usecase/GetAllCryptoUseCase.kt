package com.example.domain.usecase

import com.example.domain.database.CryptoRepository
import com.example.domain.entity.Crypto
import com.example.domain.service.CryptoService
import com.example.domain.util.Result

interface GetAllCryptoUseCase {
    operator fun invoke(): Result<List<Crypto>>
}

class GetAllCryptoUseCaseImpl(
    private val cryptoService: CryptoService,
    private val database: CryptoRepository
) : GetAllCryptoUseCase {

    override fun invoke(): Result<List<Crypto>> =
        when (val serviceResult = cryptoService.getAllCrypto()) {
            is Result.Success -> {
                database.updateCrypto(serviceResult.data)
                database.getAllCrypto()
            }
            is Result.Failure -> {
                database.getAllCrypto()
            }
        }
}
