package com.davidepani.presentation.ui.coin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidepani.domain.entities.Result
import com.davidepani.domain.usecases.GetMostCapitalizedCoinUseCase
import com.davidepani.presentation.mappers.UiMapper
import com.davidepani.presentation.models.CoinUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getMostCapitalizedCoinUseCase: GetMostCapitalizedCoinUseCase,
    private val mapper: UiMapper
) : ViewModel() {

    private val _coinLD = MutableLiveData<CoinUi>()
    val coinLD: LiveData<CoinUi> = _coinLD


    fun getCoin() {
        viewModelScope.launch {
            when (val result = getMostCapitalizedCoinUseCase()) {
                is Result.Success -> _coinLD.value = mapper.mapCoinUi(coin = result.value)
                is Result.Failure -> Log.e("CoinViewModel", result.error.toString())
            }
        }
    }

}