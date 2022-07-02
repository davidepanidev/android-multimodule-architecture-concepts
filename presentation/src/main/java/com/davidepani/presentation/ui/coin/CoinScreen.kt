package com.davidepani.presentation.ui.coin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.davidepani.presentation.R
import com.davidepani.presentation.models.CoinUi
import com.davidepani.presentation.models.CoinUiState
import com.davidepani.presentation.theme.Material3Theme
import kotlinx.coroutines.launch

@Composable
fun CoinScreen(viewModel: CoinViewModel = viewModel()) {
    val state = viewModel.uiState.observeAsState()

    state.value?.let {
        when(it) {
            is CoinUiState.Loading -> LoadingContent()
            is CoinUiState.Error -> ErrorContent(
                message = it.message,
                onRetryClick = { viewModel.onRetryButtonClick() }
            )
            is CoinUiState.Success -> CoinContent(coin = it.coin)
        }
    }

}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorContent(
    message: String,
    onRetryClick: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val actionLabel = stringResource(id = R.string.retry)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(
            hostState = snackbarHostState
        )
    }

    coroutineScope.launch {
        val result = snackbarHostState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = SnackbarDuration.Indefinite
        )

        if (result == SnackbarResult.ActionPerformed) {
            onRetryClick()
        }
    }

}

@Composable
fun CoinContent(coin: CoinUi) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = stringResource(id = R.string.most_capitalized_coin))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = coin.imageUrl),
                    contentDescription = stringResource(id = R.string.coin_image_description),
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = coin.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = coin.marketCap,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}


@Preview
@Composable
fun CoinContentPreview() {
    Material3Theme {
        CoinContent(
            coin = CoinUi(
                name = "Bitcoin",
                marketCap = "$ 1.000.435",
                imageUrl = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579"
            )
        )
    }
}