package com.davidepani.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.davidepani.presentation.databinding.MainActivityBinding
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
                    Surface(modifier = Modifier.fillMaxSize()) {
                        CoinScreen()
                    }
                }
            }
        }

        setContentView(binding.root)
    }

}