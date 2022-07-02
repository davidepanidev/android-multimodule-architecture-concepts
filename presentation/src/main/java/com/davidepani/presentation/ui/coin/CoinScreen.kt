package com.davidepani.presentation.ui.coin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorContent(
    message: String,
    onRetryClick: () -> Unit
) {

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
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = coin.marketCap,
                    style = MaterialTheme.typography.headlineLarge
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