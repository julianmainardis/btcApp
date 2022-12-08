package com.example.bitcoinapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bitcoinapp.testObserver
import com.example.domain.entity.Crypto
import com.example.domain.usecase.GetFiatPriceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FiatPriceFragmentViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: FiatPriceViewModel
    private val getFiatPriceUseCase: GetFiatPriceUseCase = mock()
    private lateinit var crypto: Crypto
    private val listOfCryptos: List<Crypto> = mock()
    private val exception: Exception = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FiatPriceViewModel(getFiatPriceUseCase)
        crypto = Crypto(currency = CURRENCY, actualValue = ACTUAL_PRICE)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getFiatPricePressedTest() {
        val liveData = viewModel.getFiatPriceState().testObserver()

        whenever(getFiatPriceUseCase(CURRENCY)).thenReturn(crypto)

        runBlocking {
            viewModel.getFiatPricePressed(CURRENCY).join()
        }

        assertEquals(
            FiatPriceViewModel.FiatPriceState.RESPONSE_SUCCESS,
            liveData.observedValues[0]?.state
        )
    }

    @Test
    fun filterTest() {
        val liveData = viewModel.getFiatPriceState().testObserver()
        val cryptoList = mutableListOf<Crypto>()
        cryptoList.add(Crypto(CURRENCY, ACTUAL_PRICE))
        cryptoList.add(Crypto(CURRENCY2, ACTUAL_PRICE))

        viewModel.filter(FILTER, cryptoList)

        assertEquals(
            FiatPriceViewModel.FiatPriceState.FILTERED,
            liveData.observedValues[0]?.state
        )

        assertEquals(
            CURRENCY,
            liveData.observedValues[0]?.filteredData?.get(0)?.currency
        )

        assertEquals(
            ACTUAL_PRICE,
            liveData.observedValues[0]?.filteredData?.get(0)?.actualValue
        )
    }

    companion object {
        private const val CURRENCY = "USD"
        private const val FILTER = "uSd"
        private const val CURRENCY2 = "GBP"
        private const val ACTUAL_PRICE = 1890f
    }
}
