package com.davidepani.data.api

import com.davidepani.data.models.CoinResponse
import retrofit2.http.GET

const val COINGECKO_API_BASE_URL = "https://api.coingecko.com"

interface CoinGeckoApiService {

    @GET("/api/v3/coins/markets")
    suspend fun getCoinsMarkets(): List<CoinResponse>

}