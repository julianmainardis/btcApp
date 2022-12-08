package com.example.data.service.response

import com.google.gson.annotations.SerializedName

data class CryptoResponse(
    @SerializedName("code")
    val currency: String,
    @SerializedName("rate_float")
    val actualValue: Float
)
