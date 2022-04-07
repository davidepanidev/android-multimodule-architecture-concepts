package com.davidepani.presentation.ui.coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.davidepani.androidextensions.views.loadImageFromUrl
import com.davidepani.architectures.viewbinding.BaseViewBindingHandlerFragment
import com.davidepani.presentation.databinding.CoinFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class CoinFragment : BaseViewBindingHandlerFragment() {

    private val binding: CoinFragmentBinding get() = requireViewBinding()
    private val viewModel: CoinViewModel by viewModels()


    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return CoinFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        viewModel.getCoin()
    }

    private fun observeData() {
        with(viewModel) {
            coinLD.observe(viewLifecycleOwner) {
                binding.tvCoinName.text = it.name
                binding.ivCoinImage.loadImageFromUrl(it.image)
                val currency = NumberFormat.getCurrencyInstance(Locale.getDefault())
                currency.maximumFractionDigits = 2
                currency.currency = Currency.getInstance("USD")
                binding.tvCoinPrice.text = currency.format(it.price)
            }
        }
    }

}