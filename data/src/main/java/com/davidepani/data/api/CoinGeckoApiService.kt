package com.davidepani.data.api

import com.davidepani.data.models.CoinApiResponse
import retrofit2.http.GET



interface CoinGeckoApiService {

    @GET("/api/v3/coins/markets")
    suspend fun getCoinsMarkets(): List<CoinApiResponse>


    companion object {
        const val BASE_URL = "https://api.coingecko.com"
    }

}