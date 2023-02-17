package com.sdk.hatlovandijon.ui.bottom.add

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
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
            val streetName = binding.etStreetName.text.toString().trim()
            val homeAddress = binding.etHomeAddress.text.toString().trim()
            val ownerHomeName = binding.etHomeBoss.text.toString().trim()
            val ownerHomeLastName = binding.etHomeBossLastName.text.toString().trim()
            val ownerYear = binding.etAge.text.toString().trim()
            val ownerGender = binding.etGen.text.toString().trim()
            val ownerNumber = binding.etNumber.text.toString().trim()
            val speakerName = binding.etName.text.toString().trim()
            val speakerLastName = binding.etLastName.text.toString().trim()
            val speakerAge = binding.etIntAge.text.toString().trim()
            val speakerGen = binding.etIntGen.text.toString().trim()
            val speakerNumber = binding.etIntNumber.text.toString().trim()
            try {
                val addData = AddData(
                    address = "$streetName, $homeAddress",
                    ownerHomeName = "$ownerHomeName $ownerHomeLastName",
                    ownerHomeYear = ownerYear.toInt(),
                    ownerHomeGender = ownerGender.toInt(),
                    ownerHomePhone = ownerNumber,
                    ownerAsSpeaker = if (binding.tvInterviewer.isChecked) 1 else 0,
                    speakerName = "$speakerName $speakerLastName",
                    speakerYear = speakerAge,
                    speakerGender = speakerGen,
                    speakerPhone = speakerNumber
                )
                val bundle = bundleOf("add_data" to addData)
                findNavController().navigate(R.id.action_addFragment_to_problemFragment, bundle)
            } catch (e: Exception) {
                e.printStackTrace()
            }
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