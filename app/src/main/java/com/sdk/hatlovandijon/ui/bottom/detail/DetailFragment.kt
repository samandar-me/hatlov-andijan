package com.sdk.hatlovandijon.ui.bottom.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentDetailBinding
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.viewBinding


class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val binding by viewBinding { FragmentDetailBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.linearLocation.click {

        }
        binding.linearNumber.click {

        }
    }
}