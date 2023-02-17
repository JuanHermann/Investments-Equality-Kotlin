package com.juanstudy.investmentsequalitykotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val api : BrapiApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://brapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BrapiApi::class.java)
    }

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl("https://brapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}