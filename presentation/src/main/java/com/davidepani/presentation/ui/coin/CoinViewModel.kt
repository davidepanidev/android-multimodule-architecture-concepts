package com.davidepani.presentation.ui.coin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.entities.Result
import com.davidepani.domain.usecases.GetMostCapitalizedCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getMostCapitalizedCoinUseCase: GetMostCapitalizedCoinUseCase
) : ViewModel() {

    private val _coinLD = MutableLiveData<Coin>()
    val coinLD: LiveData<Coin> = _coinLD


    fun getCoin() {
        viewModelScope.launch {
            when (val result = getMostCapitalizedCoinUseCase()) {
                is Result.Success -> _coinLD.value = result.value
                is Result.Failure -> Log.e("CoinViewModel", result.error.toString())
            }
        }
    }

}