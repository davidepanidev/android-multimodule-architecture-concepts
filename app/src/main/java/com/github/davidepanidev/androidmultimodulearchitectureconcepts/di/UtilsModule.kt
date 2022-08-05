package com.github.davidepanidev.androidmultimodulearchitectureconcepts.di

import com.github.davidepanidev.androidextensions.utils.imageloader.GlideImageLoader
import com.github.davidepanidev.androidextensions.utils.imageloader.ImageLoader
import com.github.davidepanidev.kotlinextensions.utils.currencyformatter.CurrencyFormatter
import com.github.davidepanidev.kotlinextensions.utils.currencyformatter.CustomCurrencyFormatter
import com.github.davidepanidev.kotlinextensions.utils.currencyformatter.models.CurrencySymbolPosition
import com.github.davidepanidev.kotlinextensions.utils.currencyformatter.models.CurrencySymbolSpacing
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