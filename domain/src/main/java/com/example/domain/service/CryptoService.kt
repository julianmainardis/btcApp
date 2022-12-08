package com.example.domain.service

import com.example.domain.entity.Crypto
import com.example.domain.util.Result

interface CryptoService {
    fun getAllCrypto(): Result<List<Crypto>>
}
