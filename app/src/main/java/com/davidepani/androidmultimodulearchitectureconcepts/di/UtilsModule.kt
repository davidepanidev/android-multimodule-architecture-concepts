package com.davidepani.androidmultimodulearchitectureconcepts.di

import com.davidepani.androidextensions.utils.imageloader.GlideImageLoader
import com.davidepani.androidextensions.utils.imageloader.ImageLoader
import com.davidepani.kotlinextensions.utils.currencyformatter.CurrencyFormatter
import com.davidepani.kotlinextensions.utils.currencyformatter.CustomCurrencyFormatter
import com.davidepani.kotlinextensions.utils.currencyformatter.models.CurrencySymbolPosition
import com.davidepani.kotlinextensions.utils.currencyformatter.models.CurrencySymbolSpacing
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideCurrencyFormatter(): CurrencyFormatter {
        return CustomCurrencyFormatter(
            currencySymbol = "$",
            currencySymbolPosition = CurrencySymbolPosition.RIGHT,
            currencySymbolSpacing = CurrencySymbolSpacing.ENABLED
        )
    }

    @Provides
    @Singleton
    fun provideImageLoader(): ImageLoader {
        return GlideImageLoader()
    }

}