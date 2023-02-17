package com.juanstudy.investmentsequalitykotlin.api

import com.juanstudy.investmentsequalitykotlin.models.PapersList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BrapiApi {

    @GET("quote/{assets}")
    suspend fun getPapers(@Path(value = "assets") assets: String): Response<PapersList>
}