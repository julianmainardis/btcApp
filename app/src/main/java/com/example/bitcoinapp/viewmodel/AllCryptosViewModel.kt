package com.example.bitcoinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Crypto
import com.example.domain.usecase.GetAllCryptoUseCase
import com.example.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllCryptosViewModel(private val getAllCryptoUseCase: GetAllCryptoUseCase) : ViewModel() {

    private var cryptoState = MutableLiveData<CryptoData>()
    fun getAllCryptoState(): LiveData<CryptoData> = cryptoState

    fun getAllPressed() = viewModelScope.launch {
        withContext(Dispatchers.IO) { getAllCryptoUseCase() }.let { result ->
            when (result) {
                is Result.Success -> {
                    cryptoState.postValue(
                        CryptoData(
                            state = CryptoState.RESPONSE_SUCCESS,
                            data = result.data
                        )
                    )
                }
                is Result.Failure -> {
                    cryptoState.postValue(CryptoData(state = CryptoState.RESPONSE_ERROR))
                }
            }
        }
    }

    data class CryptoData(
        val state: CryptoState,
        val data: List<Crypto> = emptyList()
    )

    enum class CryptoState {
        RESPONSE_SUCCESS,
        RESPONSE_ERROR
    }
}
