package com.juanstudy.investmentsequalitykotlin.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.juanstudy.investmentsequalitykotlin.api.RetrofitHelper
import com.juanstudy.investmentsequalitykotlin.db.AssetRepository
import com.juanstudy.investmentsequalitykotlin.models.Asset
import com.juanstudy.investmentsequalitykotlin.models.PapersList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AssetRepository(application)
    val assetList = listOf(Asset("PETR4"), Asset("MGLU3"), Asset("JSRE11"))

    var allAssetsSaved: MutableLiveData<List<Asset>> = MutableLiveData()

    val assetListString = assetList.joinToString(",")

    init {
        CoroutineScope(Dispatchers.IO).launch {
            allAssetsSaved.postValue(repository.getAll())
        }
    }

    private var papersList: LiveData<Response<PapersList>> = liveData {
        val data = RetrofitHelper.api.getPapers(assetListString)
        emit(data)
    }

    fun getAll() {

        CoroutineScope(Dispatchers.IO).launch {
            allAssetsSaved.postValue(getDetailsAssets(repository.getAll()))

        }
    }

    private fun getDetailsAssets(assets: List<Asset>): List<Asset> {

//        CoroutineScope(Dispatchers.IO).launch {
//            val assetsDetails = RetrofitHelper.api.getPapers(assets.joinToString(","))
//            if (assetsDetails.isSuccessful && assetsDetails.body() != null && assetsDetails.body().results.isEmpty()) {
//
//                for (asset in assets) {
//                    val details = assetsDetails.body()
//
//                    for (detail in details.results) {
//                        if (asset.tag == detail.symbol) {
//                            asset.price = detail.
//                        }
//                    }
//                }
//
//
//            }
//        }
        return assets

    }


    fun insert(asset: Asset) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insert(asset)
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }
}



