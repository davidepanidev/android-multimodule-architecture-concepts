package com.davidepani.presentation.ui.coin

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

    private val _errorLD = MutableLiveData<String>()
    val errorLD: LiveData<String> = _errorLD

    private val _isProgressVisible = MutableLiveData<Boolean>()
    val isProgressVisible: LiveData<Boolean> = _isProgressVisible

    private val _isCoinContentVisible = MutableLiveData<Boolean>()
    val isCoinContentVisible: LiveData<Boolean> = _isCoinContentVisible

    init {
        hideCoinContent()
    }


    fun getCoin() {
        showProgress()

        viewModelScope.launch {
            when (val result = getMostCapitalizedCoinUseCase()) {
                is Result.Success -> {
                    _coinLD.value = mapper.mapCoinUi(coin = result.value)
                    showCoinContent()
                }
                is Result.Failure -> {
                    _errorLD.value = result.error.message
                }
            }

            hideProgress()
        }
    }

    fun onRetryButtonClick() {
        getCoin()
    }

    private fun showProgress() {
        _isProgressVisible.value = true
    }

    private fun hideProgress() {
        _isProgressVisible.value = false
    }

    private fun showCoinContent() {
        _isCoinContentVisible.value = true
    }

    private fun hideCoinContent() {
        _isCoinContentVisible.value = false
    }

}