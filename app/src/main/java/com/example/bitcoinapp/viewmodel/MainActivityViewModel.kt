package com.example.bitcoinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var mainActivityState = MutableLiveData<MainActivityData>()
    fun getMainActivityState(): LiveData<MainActivityData> = mainActivityState

    fun getAllPressed() {
        mainActivityState.value = MainActivityData(MainActivityState.GET_ALL)
    }

    fun getFiatPricePressed() {
        mainActivityState.value = MainActivityData(MainActivityState.GET_FIAT_TYPE)
    }

    data class MainActivityData(
        val state: MainActivityState
    )

    enum class MainActivityState {
        GET_ALL,
        GET_FIAT_TYPE,
    }
}
