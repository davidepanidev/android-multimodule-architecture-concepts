package com.davidepani.presentation.ui.coin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidepani.domain.entities.Result
import com.davidepani.domain.usecases.GetMostCapitalizedCoinUseCase
import com.davidepani.presentation.mappers.UiMapper
import com.davidepani.presentation.models.CoinUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getMostCapitalizedCoinUseCase: GetMostCapitalizedCoinUseCase,
    private val mapper: UiMapper
) : ViewModel() {

    private val _uiState = MutableLiveData<CoinUiState>()
    val uiState: LiveData<CoinUiState> = _uiState


    init {
        getCoin()
    }

    fun getCoin() {
        _uiState.value = CoinUiState.Loading

        viewModelScope.launch {
            when (val result = getMostCapitalizedCoinUseCase()) {
                is Result.Success -> {
                    _uiState.value = CoinUiState.Success(mapper.mapCoinUi(coin = result.value))
                }
                is Result.Failure -> {
                    _uiState.value = CoinUiState.Error(result.error.toString())
                }
            }
        }
    }

    fun onRetryButtonClick() {
        getCoin()
    }

}