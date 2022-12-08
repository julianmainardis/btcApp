package com.example.bitcoinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Crypto
import com.example.domain.usecase.GetFiatPriceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FiatPriceViewModel(private val getFiatPriceUseCase: GetFiatPriceUseCase) : ViewModel() {

    private var fiatPriceState = MutableLiveData<FiatPriceData>()
    fun getFiatPriceState(): LiveData<FiatPriceData> = fiatPriceState

    fun getFiatPricePressed(selected: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) { getFiatPriceUseCase(selected) }.let { result ->
            fiatPriceState.postValue(
                FiatPriceData(
                    state = FiatPriceState.RESPONSE_SUCCESS,
                    data = result
                )
            )
        }
    }

    fun filter(filter: String, cryptoList: List<Crypto>) {
        val filteredList =
            cryptoList.filter { it.currency.uppercase().contains(filter.uppercase()) }
        fiatPriceState.postValue(
            FiatPriceData(
                state = FiatPriceState.FILTERED,
                filteredData = filteredList
            )
        )
    }

    data class FiatPriceData(
        val state: FiatPriceState,
        val filteredData: List<Crypto> = emptyList(),
        val data: Crypto? = null
    )

    enum class FiatPriceState {
        RESPONSE_SUCCESS,
        FILTERED
    }
}
