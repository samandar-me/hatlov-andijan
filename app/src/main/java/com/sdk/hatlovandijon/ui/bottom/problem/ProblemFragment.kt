package com.sdk.hatlovandijon.ui.bottom.problem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentProblemBinding
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.viewBinding


class ProblemFragment : BaseFragment(R.layout.fragment_problem) {
    private val binding by viewBinding { FragmentProblemBinding.bind(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}