package com.juanstudy.investmentsequalitykotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanstudy.investmentsequalitykotlin.adapter.AssetsAdapter
import com.juanstudy.investmentsequalitykotlin.api.BrapiApi
import com.juanstudy.investmentsequalitykotlin.api.RetrofitHelper
import com.juanstudy.investmentsequalitykotlin.databinding.ActivityMainBinding
import com.juanstudy.investmentsequalitykotlin.dialog.DialogAssetFragment
import com.juanstudy.investmentsequalitykotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AssetsAdapter
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(application))[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
        setAdapter()
        setOnClick()

        callApi()

    }

    private fun callApi() {
        Log.d("JUAN: ", "começo")

        val quotesApi = RetrofitHelper.getInstance().create(BrapiApi::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("JUAN: ", quotesApi.getPapers("PETR4,MGLU3,Jsre11").body().toString())
            binding.srlAssets.isRefreshing = false
        }
        Log.d("JUAN: ", "fim")
    }


    private fun setOnClick() {
        binding.fab.setOnClickListener {
            binding.fragmentContainer.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainer.id, DialogAssetFragment()).commit()
        }
        binding.srlAssets.setOnRefreshListener {
                viewModel.getAll()
            Log.d("JUAN: ", "setOnRefreshListener")

        }
    }

    private fun setObservers() {


        viewModel.papersList.observe(this, Observer {
            Log.d("JUAN: ", "result do observer")
            Log.d("JUAN: ", it.body().toString())

        })

        viewModel.allAssetsSaved.observe(this, Observer {
            Log.d("JUAN: ", "allAssets Observer")

            adapter.assets = it
            adapter.notifyDataSetChanged()
            binding.srlAssets.isRefreshing = false
        })
    }

    private fun setAdapter() {
        adapter = AssetsAdapter(emptyList())
        binding.listAssets.layoutManager = LinearLayoutManager(this)
        binding.listAssets.adapter = adapter
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments
        if (fragmentList.size == 0) {
            super.onBackPressed()
        } else {
            for (frag in fragmentList) {
                supportFragmentManager.beginTransaction().remove(frag!!).commit()
            }
            binding.fragmentContainer.visibility = View.GONE
        }
    }

}