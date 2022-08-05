package com.github.davidepanidev.presentation.ui.coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.github.davidepanidev.androidarchitectures.viewbinding.BaseViewBindingHandlerFragment
import com.github.davidepanidev.androidextensions.utils.imageloader.ImageLoader
import com.github.davidepanidev.androidextensions.views.loadImageFromUrl
import com.github.davidepanidev.presentation.R
import com.github.davidepanidev.presentation.databinding.CoinFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoinFragment : BaseViewBindingHandlerFragment() {

    private val binding: CoinFragmentBinding get() = requireViewBinding()
    private val viewModel: CoinViewModel by viewModels()

    @Inject lateinit var imageLoader: ImageLoader


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
                with(binding) {
                    tvCoinName.text = it.name
                    tvCoinMarketCap.text = it.marketCap
                    ivCoinImage.loadImageFromUrl(
                        url = it.imageUrl,
                        imageLoader = imageLoader
                    )
                }
            }

            errorLD.observe(viewLifecycleOwner) {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry) {
                        viewModel.onRetryButtonClick()
                    }.show()
            }

            isProgressVisible.observe(viewLifecycleOwner) {
                binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            }

            isCoinContentVisible.observe(viewLifecycleOwner) {
                binding.grCoinContent.visibility = if (it) View.VISIBLE else View.GONE
            }

        }
    }

}