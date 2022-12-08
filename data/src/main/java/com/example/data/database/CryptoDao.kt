package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.CryptoEntity

@Dao
interface CryptoDao {
    @Query("SELECT * FROM crypto_table")
    fun getAllCrypto(): List<CryptoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCrypto(cryptoEntity: CryptoEntity)

    @Query("SELECT * FROM crypto_table WHERE currency = :selection")
    fun getFiatPrice(selection: String): CryptoEntity
}
