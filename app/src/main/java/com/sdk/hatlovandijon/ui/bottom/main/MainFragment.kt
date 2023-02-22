package com.sdk.hatlovandijon.ui.bottom.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentMainBinding
import com.sdk.hatlovandijon.ui.adapter.AppealAdapter
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.Constants.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val appealAdapter by lazy { AppealAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        observeState()
        observeUser()

        binding.rv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = appealAdapter
        }
        appealAdapter.onClick = {
            val bundle = bundleOf("id" to it)
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
        binding.editSearch.addTextChangedListener {
            it?.let { str ->
                if (str.toString().isEmpty()) {
                    binding.lottie.isVisible = false
                    appealAdapter.submitList(viewModel.savedAppealList)
                }
            }
        }
        binding.editSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchAppeal()
            }
            true
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is MainState.Loading -> {
                        binding.shimmer.isVisible = true
                        binding.shimmer.startShimmer()
                        binding.rv.isVisible = false
                        delay(2000L)
                        binding.lottie.isVisible = false
                    }
                    is MainState.Error -> {
                        binding.shimmer.stopShimmer()
                        binding.rv.isVisible = false
                        snack(getString(R.string.error_occ), false)
                    }
                    is MainState.SuccessAppeals -> {
                        binding.shimmer.stopShimmer()
                        binding.rv.isVisible = true
                        binding.shimmer.isVisible = false
                        val mah = state.data.data.mahalla
                        binding.tvMah.text = "${mah.tuman}, ${mah.name}"
                        appealAdapter.submitList(state.data.data.murojaatlar)
                    }
                    is MainState.SuccessSearchAppeals -> {
                        binding.shimmer.stopShimmer()
                        binding.rv.isVisible = true
                        binding.shimmer.isVisible = false
                        binding.lottie.isVisible = state.appeals.isEmpty()
                        appealAdapter.submitList(state.appeals)
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

    private fun searchAppeal() {
        val query = binding.editSearch.text.toString().trim()
        if (query.isNotBlank()) {
            viewModel.onEvent(MainEvent.OnSearchAppeal(query))
        } else {
            snack(getString(R.string.enter_query), false)
        }
    }
}