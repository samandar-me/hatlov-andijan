package com.sdk.hatlovandijon.ui.bottom.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentMainBinding
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.viewBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cardView.click {
                findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
            }
        }
    }
}