package com.example.data

import com.example.data.entity.CryptoEntity
import com.example.data.util.transformToLocalCrypto
import com.example.data.util.transformToLocalCryptoList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CryptoEntityMapperTest {

    private lateinit var cryptoEntity: CryptoEntity
    private lateinit var cryptoEntityList: List<CryptoEntity>

    @Before
    fun init() {
        cryptoEntity =
            CryptoEntity(currency = CURRENCY, actualValue = ACTUAL_PRICE)
        cryptoEntityList =
            mutableListOf(
                CryptoEntity(currency = CURRENCY, actualValue = ACTUAL_PRICE)
            )
    }

    @Test
    fun `transform entity crypto to local crypto`() {
        val cryptoEntityTransformed = cryptoEntity.transformToLocalCrypto()
        assertEquals(cryptoEntityTransformed.currency, cryptoEntity.currency)
        assertEquals(cryptoEntityTransformed.actualValue, cryptoEntity.actualValue)
    }

    @Test
    fun `transform list of entity crypto to list of local crypto`() {
        val cryptoEntityListTransformed = cryptoEntityList.transformToLocalCryptoList()
        assertEquals(cryptoEntityListTransformed[0].currency, cryptoEntityList[0].currency)
        assertEquals(cryptoEntityListTransformed[0].actualValue, cryptoEntityList[0].actualValue)
    }

    companion object {
        private const val CURRENCY = "USD"
        private const val ACTUAL_PRICE = 1890f
    }
}
