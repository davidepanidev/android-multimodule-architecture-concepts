package com.github.davidepanidev.presentation.models

sealed class CoinUiState {
    object Loading : CoinUiState()
    data class Success(val coin: CoinUi) : CoinUiState()
    data class Error(val message: String) : CoinUiState()
}
