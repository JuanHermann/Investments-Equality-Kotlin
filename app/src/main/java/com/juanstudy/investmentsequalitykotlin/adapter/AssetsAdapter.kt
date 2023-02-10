package com.juanstudy.investmentsequalitykotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanstudy.investmentsequalitykotlin.databinding.AdapterAssetsBinding
import com.juanstudy.investmentsequalitykotlin.models.Asset

class AssetsAdapter(private val assets: List<Asset>) :
    RecyclerView.Adapter<AssetsAdapter.AssetViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val binding =
            AdapterAssetsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AssetViewHolder(binding)
    }

    override fun getItemCount(): Int = assets.size

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) =
        holder.bind(assets[position])


    class AssetViewHolder(private val binding: AdapterAssetsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(asset: Asset) {
            binding.tvAssetName.text = asset.tag
            binding.tvAssetDateCom.text = asset.id.toString() + "/12"
            binding.tvAssetPercent.text = "R$ " + asset.price.toString()
        }
    }
}
