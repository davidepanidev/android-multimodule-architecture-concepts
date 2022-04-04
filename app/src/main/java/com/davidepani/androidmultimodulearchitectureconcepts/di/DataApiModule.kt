package com.davidepani.androidmultimodulearchitectureconcepts.di

import com.davidepani.data.api.CoinGeckoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataApiModule {

    @Provides
    @Singleton
    fun provideCoinGeckoApiService(): CoinGeckoApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(CoinGeckoApiService.BASE_URL)
        .build()
        .create(CoinGeckoApiService::class.java)

}