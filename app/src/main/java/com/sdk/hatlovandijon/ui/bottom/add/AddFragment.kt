package com.sdk.hatlovandijon.ui.bottom.add

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentAddBinding
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.viewBinding

class AddFragment : BaseFragment(R.layout.fragment_add) {
    private val binding by viewBinding { FragmentAddBinding.bind(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTexts()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        var isVisible = false
        binding.tvInterviewer.click {
            isVisible = !isVisible
            binding.tvInterviewer.isChecked = !isVisible
            binding.linearInterviewer.isVisible = isVisible
            binding.tvInterviewer.textColor(if (isVisible) R.color.gray else R.color.blue)
        }
        binding.btnContinue.click {
            findNavController().navigate(R.id.action_addFragment_to_problemFragment)
        }
    }

    private fun editTexts() {
        binding.apply {
            val editTextList = mapOf(
                etStreetName to tvStreetName,
                etHomeAddress to tvHomeAddress,
                etHomeBoss to tvHomeBoss,
                etAge to tvAge,
                etGen to tvGen,
                etNumber to tvNumber,
                etName to tvName,
                etLastName to tvLastName,
                etIntAge to tvIntAge,
                etIntNumber to tvIntNumber
            )
            editTextList.forEach {
                it.key.sutUpInput(it.value)
            }
        }
    }
}