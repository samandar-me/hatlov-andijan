package com.sdk.hatlovandijon.ui.bottom.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.google.android.material.textfield.TextInputEditText
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentAddBinding
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.viewBinding

class AddFragment : BaseFragment(R.layout.fragment_add) {
    private val binding by viewBinding { FragmentAddBinding.bind(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTexts()
    }
    private fun editTexts() {
        binding.apply {
            etStreetName.sutUpInput(binding.tvStreetName)
        }
    }
}