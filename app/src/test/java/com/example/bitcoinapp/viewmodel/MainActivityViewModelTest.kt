package com.example.bitcoinapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        mainActivityViewModel = MainActivityViewModel()
    }

    @Test
    fun `testing when the Get All Cryptos button is pressed`() {
        mainActivityViewModel.getAllPressed()

        Assert.assertEquals(
            MainActivityViewModel.MainActivityState.GET_ALL,
            mainActivityViewModel.getMainActivityState().value?.state
        )
    }

    @Test
    fun `testing when the Get Fiat Type button is pressed`() {
        mainActivityViewModel.getFiatPricePressed()

        Assert.assertEquals(
            MainActivityViewModel.MainActivityState.GET_FIAT_TYPE,
            mainActivityViewModel.getMainActivityState().value?.state
        )
    }
}
