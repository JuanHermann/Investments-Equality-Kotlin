package com.juanstudy.investmentsequalitykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanstudy.investmentsequalitykotlin.adapter.AssetsAdapter
import com.juanstudy.investmentsequalitykotlin.databinding.ActivityMainBinding
import com.juanstudy.investmentsequalitykotlin.models.Asset

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //    private val adapter:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setViewModel()
    }

    private fun setAdapter(){
        binding.listAssets.layoutManager = LinearLayoutManager(this)
        binding.listAssets.adapter = AssetsAdapter(listOf( Asset(1,"LOGG3",1,100.0,1)))
    }

    private fun setViewModel(){

    }
}