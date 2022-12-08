package com.example.data.service.api


import com.example.data.service.response.ResultResponse
import retrofit2.Call
import retrofit2.http.GET

interface CryptoApi {
    @GET("v1/bpi/currentprice.json")
    fun getAllCrypto(): Call<ResultResponse>
}
