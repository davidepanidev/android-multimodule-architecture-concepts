package com.davidepani.presentation.ui.coin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidepani.domain.entities.Coin
import com.davidepani.domain.usecases.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    private val _coinLD = MutableLiveData<Coin>()
    val coinLD: LiveData<Coin> = _coinLD


    init {
        getCoin()
    }

    fun getCoin() {
        viewModelScope.launch {
            val coin = getCoinUseCase()
            _coinLD.value = coin
        }
    }

}