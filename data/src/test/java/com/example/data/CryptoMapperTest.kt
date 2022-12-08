package com.example.data

import com.example.data.util.transformToCryptoEntity
import com.example.domain.entity.Crypto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CryptoMapperTest {

    private lateinit var crypto: Crypto

    @Before
    fun init() {
        crypto = Crypto(currency = CURRENCY, actualValue = ACTUAL_PRICE)
    }

    @Test
    fun `transform local movie to entity movie`() {
        val localCryptoTransformed = crypto.transformToCryptoEntity()
        assertEquals(localCryptoTransformed.currency, crypto.currency)
        assertEquals(localCryptoTransformed.actualValue, crypto.actualValue)
    }

    companion object {
        private const val CURRENCY = "USD"
        private const val ACTUAL_PRICE = 1890f
    }
}
