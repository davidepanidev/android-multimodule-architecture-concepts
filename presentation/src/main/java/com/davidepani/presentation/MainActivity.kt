package com.davidepani.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davidepani.presentation.databinding.MainActivityBinding
import com.davidepani.presentation.models.CoinUi
import com.davidepani.presentation.theme.Material3Theme
import com.davidepani.presentation.ui.coin.CoinScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater).apply {
            composeView.setContent {
                Material3Theme {
                    CoinScreen(
                        coin = CoinUi(
                            name = "Bitcoin",
                            marketCap = "$ 1.000.435",
                            imageUrl = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579"
                        )
                    )
                }
            }
        }

        setContentView(binding.root)
    }

}