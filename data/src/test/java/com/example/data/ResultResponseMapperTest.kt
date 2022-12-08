package com.example.data

import com.example.data.service.response.CryptoResponse
import com.example.data.service.response.ResultResponse
import com.example.data.util.transformToLocalCryptoList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResultResponseMapperTest {

    private lateinit var resultResponse: ResultResponse

    @Before
    fun init() {
        resultResponse =
            ResultResponse(
                mutableListOf(
                    CryptoResponse(
                        currency = CURRENCY,
                        actualValue = ACTUAL_PRICE
                    )
                )
            )
    }

    @Test
    fun `transform data response to local crypto list`() {
        val response = resultResponse.transformToLocalCryptoList()
        assertEquals(response[0].currency, resultResponse.results[0].currency)
        assertEquals(response[0].actualValue, resultResponse.results[0].actualValue)
    }

    companion object {
        private const val CURRENCY = "USD"
        private const val ACTUAL_PRICE = 1890f
    }
}
