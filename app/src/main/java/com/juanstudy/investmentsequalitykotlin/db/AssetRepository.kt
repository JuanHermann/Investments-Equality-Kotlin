package com.juanstudy.investmentsequalitykotlin.db

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.juanstudy.investmentsequalitykotlin.models.Asset
import kotlinx.coroutines.flow.Flow

class AssetRepository(private val application: Application) {

  suspend  fun getAll(): List<Asset> {
        return getAssetDAO().getAll()
    }

    suspend fun insert(asset: Asset?) {
        try {
            asset?.let {
                getAssetDAO().insert(asset)
            }
        } catch (e: Exception) {
            Log.e("JUAN", "erro na hora de salvar o asset = " + e.message)
        }
    }

    private fun getAssetDAO(): AssetDao {
        return DatabaseApp.getDatabase(application).assetDao()
    }
}