package com.github.davidepanidev.data.api

import com.github.davidepanidev.data.models.CoinApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CoinGeckoApiService {

    @GET("/api/v3/coins/markets")
    suspend fun getCoinsMarkets(@Query("vs_currency") currency: String = "usd", @Query("per_page") numCoinsPerPage: Int = 100, @Query("order") order: String = "market_cap_desc"): List<CoinApiResponse>


    companion object {
        const val BASE_URL = "https://api.coingecko.com"
    }

}