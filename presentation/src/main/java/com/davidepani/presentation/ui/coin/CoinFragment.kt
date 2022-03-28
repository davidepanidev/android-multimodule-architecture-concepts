package com.davidepani.presentation.ui.coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.davidepani.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinFragment : Fragment() {

    companion object {
        fun newInstance() = CoinFragment()
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.coin_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        // TODO: Use the ViewModel
    }

}