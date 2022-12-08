package com.example.bitcoinapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bitcoinapp.testObserver
import com.example.domain.entity.Crypto
import com.example.domain.usecase.GetAllCryptoUseCase
import com.example.domain.util.Result
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
class AllCryptoFragmentViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: AllCryptosViewModel
    private val getAllCryptoUseCae: GetAllCryptoUseCase = mock()
    private val listOfCryptos: List<Crypto> = mock()
    private val exception: Exception = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        viewModel = AllCryptosViewModel(getAllCryptoUseCae)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getAllCryptos return success result`() {
        val liveData = viewModel.getAllCryptoState().testObserver()

        whenever(getAllCryptoUseCae()).thenReturn(Result.Success(listOfCryptos))

        runBlocking {
            viewModel.getAllPressed().join()
        }

        assertEquals(
            AllCryptosViewModel.CryptoState.RESPONSE_SUCCESS,
            liveData.observedValues[0]?.state
        )
    }

    @Test
    fun `when getNowPlayingMovies return failure`() {
        val liveData = viewModel.getAllCryptoState().testObserver()

        whenever(getAllCryptoUseCae()).thenReturn(Result.Failure(exception = exception))

        runBlocking {
            viewModel.getAllPressed().join()
        }

        assertEquals(
            AllCryptosViewModel.CryptoState.RESPONSE_ERROR,
            liveData.observedValues[0]?.state
        )
    }
}
