package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_table")
class CryptoEntity(
    @PrimaryKey
    val currency: String,
    val actualValue: Float
)
