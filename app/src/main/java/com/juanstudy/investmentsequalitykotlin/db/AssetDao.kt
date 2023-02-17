package com.juanstudy.investmentsequalitykotlin.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juanstudy.investmentsequalitykotlin.models.Asset


@Dao
interface AssetDao {
    @Query("SELECT * FROM assets")
  suspend  fun getAll(): List<Asset>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(asset: Asset)

    @Query("DELETE FROM assets")
    suspend fun deleteAll()

}