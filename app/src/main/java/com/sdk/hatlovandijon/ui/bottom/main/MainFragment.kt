package com.sdk.hatlovandijon.ui.bottom.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentMainBinding
import com.sdk.hatlovandijon.ui.adapter.AppealAdapter
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel: MainViewModel by viewModels()
    private val appealAdapter by lazy { AppealAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        observeUser()

        binding.rv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = appealAdapter
        }
        appealAdapter.onClick = {
            val bundle = bundleOf("appeal" to it)
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }
    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when(state) {
                    is MainState.Loading -> {
                        binding.shimmer.startShimmer()
                        binding.rv.isVisible = false
                        delay(2000L)
                    }
                    is MainState.Error -> {
                        binding.shimmer.stopShimmer()
                        binding.rv.isVisible = false
                        snack(state.message, false)
                    }
                    is MainState.SuccessAppeals -> {
                        binding.shimmer.stopShimmer()
                        binding.rv.isVisible = true
                        binding.shimmer.isVisible = false
                        val mah = state.data.data.mahalla
                        binding.tvMah.text = "${mah.tuman}, ${mah.name}"
                        appealAdapter.submitList(state.data.data.murojaatlar)
                    }
                }
            }
        }
    }
    private fun observeUser() {
        lifecycleScope.launch {
            viewModel.user.collect {
                it?.let { user ->
                    binding.apply {
                        textHello.text = "${getText(R.string.hello)} ${user.fullName}"
                        textKo.text = user.neiName
                    }
                }
            }
        }
    }
}