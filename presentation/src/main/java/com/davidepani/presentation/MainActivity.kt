package com.davidepani.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davidepani.presentation.ui.coin.CoinFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CoinFragment.newInstance())
                .commitNow()
        }
    }
}