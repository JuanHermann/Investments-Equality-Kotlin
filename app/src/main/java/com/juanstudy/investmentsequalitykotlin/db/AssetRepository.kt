package com.juanstudy.investmentsequalitykotlin.db

import androidx.annotation.WorkerThread
import com.juanstudy.investmentsequalitykotlin.models.Asset

class AssetRepository(private val assetDao: AssetDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: List<Asset> = assetDao.getAssets()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(asset: Asset) {
        assetDao.insert(asset)
    }
}