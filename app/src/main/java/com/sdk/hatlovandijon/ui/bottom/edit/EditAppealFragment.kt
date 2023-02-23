package com.sdk.hatlovandijon.ui.bottom.edit

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import com.sdk.domain.model.detail.Data
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentAddBinding
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.ui.bottom.add.AddData
import com.sdk.hatlovandijon.util.splitText
import com.sdk.hatlovandijon.util.viewBinding

class EditAppealFragment : BaseFragment(R.layout.fragment_add) {
    private val binding by viewBinding { FragmentAddBinding.bind(it) }
    private var data: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = arguments?.getParcelable("data") as? Data
    }
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
            findNavController().navigate(R.id.action_editAppealFragment_to_addEditAppealFragment)
            return@click
            val streetName = binding.etStreetName.text.toString().trim()
            val homeAddress = binding.etHomeAddress.text.toString().trim()
            val ownerHomeName = binding.etHomeBoss.text.toString().trim()
            val ownerGen = binding.etOwnerGen.text.toString().trim()
            val ownerHomeLastName = binding.etHomeBossLastName.text.toString().trim()
            val ownerYear = binding.etAge.text.toString().trim()
            val ownerNumber = binding.etNumber.text.toString().trim()
            val speakerName = binding.etName.text.toString().trim()
            val speakerLastName = binding.etLastName.text.toString().trim()
            val speakerAge = binding.etIntAge.text.toString().trim()
            val speakerGen = binding.etSpeakerGen.text.toString().trim()
            val speakerNumber = binding.etIntNumber.text.toString().trim()
            try {
                val addData = AddData(
                    id = data?.id ?: -1,
                    address = "$streetName, $homeAddress",
                    ownerHomeName = "$ownerHomeName $ownerHomeLastName",
                    ownerHomeYear = ownerYear.toInt(),
                    ownerHomeGender = if (ownerGen == getString(R.string.male)) 1 else 0,
                    ownerHomePhone = ownerNumber,
                    ownerAsSpeaker = if (binding.tvInterviewer.isChecked) 1 else 0,
                    speakerName = "$speakerName $speakerLastName",
                    speakerYear = speakerAge,
                    speakerGender = speakerGen,
                    speakerPhone = speakerNumber,
                    problemContent = data?.turi?.name ?: "",
                    comment = data?.izoh ?: "",
                    oldImages = data?.images ?: emptyList()
                )
                val bundle = bundleOf("add_data" to addData)
                findNavController().navigate(R.id.action_editAppealFragment_to_addEditAppealFragment, bundle)
            } catch (e: Exception) {
                snack(getString(R.string.enter_correct_data), false)
                e.printStackTrace()
            }
        }
        showUI()
        binding.apply {
            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listOf(getString(R.string.male), getString(R.string.female)))
            etSpeakerGen.setAdapter(arrayAdapter)
            etOwnerGen.setAdapter(arrayAdapter)
        }
    }

    private fun editTexts() {
        binding.apply {
            val editTextList = mapOf(
                etStreetName to tvStreetName,
                etHomeAddress to tvHomeAddress,
                etHomeBoss to tvHomeBoss,
                etAge to tvAge,
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
    private fun showUI() {
        data?.let { d ->
            binding.apply {
                etStreetName.setText(d.mahalla.splitText())
                etOwnerGen.setText(d.owner_home_jinsi.splitText())
                etHomeAddress.setText(d.address.splitText())
                etHomeBoss.setText(d.owner_home_name.subSequence(0, d.owner_home_name.indexOf(" ")).toString().splitText())
                etHomeBossLastName.setText(d.owner_home_name.subSequence(d.owner_home_name.indexOf(" "), d.owner_home_name.length -1).toString().splitText())
                etAge.setText(d.owner_home_year.toString().splitText())
                etNumber.setText(d.owner_home_phone.splitText())
                etName.setText((d.speaker_name ?: "").splitText())
                etLastName.setText((d.speaker_name ?: "").splitText())
                etIntAge.setText((d.speaker_year ?: 0).toString().splitText())
                etIntNumber.setText((d.speaker_phone ?: "").splitText())
            }
        }
    }
}