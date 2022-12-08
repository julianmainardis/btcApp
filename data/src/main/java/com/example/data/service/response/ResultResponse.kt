package com.example.data.service.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("bpi")
    val results: MutableList<CryptoResponse>
)
