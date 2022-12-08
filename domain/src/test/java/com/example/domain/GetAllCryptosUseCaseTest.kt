package com.example.domain

import com.example.domain.database.CryptoRepository
import com.example.domain.entity.Crypto
import com.example.domain.service.CryptoService
import com.example.domain.usecase.GetAllCryptoUseCase
import com.example.domain.usecase.GetAllCryptoUseCaseImpl
import com.example.domain.util.Result
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetAllCryptosUseCaseTest {

    private lateinit var getAllCryptosUseCase: GetAllCryptoUseCase
    private val cryptoService: CryptoService = mock()
    private val database: CryptoRepository = mock()
    private val cryptoList: List<Crypto> = mock()

    @Before
    fun init() {
        getAllCryptosUseCase = GetAllCryptoUseCaseImpl(cryptoService, database)
    }

    @Test
    fun `when the service return a success result`() {
        whenever(cryptoService.getAllCrypto()).thenReturn(Result.Success(cryptoList))
        whenever(database.getAllCrypto()).thenReturn(Result.Success(cryptoList))

        val result = getAllCryptosUseCase()

        verify(database).updateCrypto(cryptoList)
        verify(database).getAllCrypto()

        Assert.assertEquals(cryptoList, (result as Result.Success).data)
    }

    @Test
    fun `when the service return a failure result and the database is empty`() {
        whenever(cryptoService.getAllCrypto()).thenReturn(Result.Failure(Exception(NOT_FOUND)))
        whenever(database.getAllCrypto()).thenReturn(Result.Failure(Exception(NOT_FOUND)))

        val result = getAllCryptosUseCase()

        verify(database).getAllCrypto()

        Assert.assertEquals(NOT_FOUND, (result as Result.Failure).exception.message)
    }

    @Test
    fun `when the service return a failure result and the database isn't empty`() {
        whenever(cryptoService.getAllCrypto()).thenReturn(Result.Failure(Exception(NOT_FOUND)))
        whenever(database.getAllCrypto()).thenReturn(Result.Success(cryptoList))

        val result = getAllCryptosUseCase()

        verify(database).getAllCrypto()

        Assert.assertEquals(cryptoList, (result as Result.Success).data)
    }

    companion object {
        private const val NOT_FOUND = "NOT_FOUND"
    }
}
