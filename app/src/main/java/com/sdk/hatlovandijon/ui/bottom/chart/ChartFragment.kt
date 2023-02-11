package com.sdk.hatlovandijon.ui.bottom.chart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentChartBinding
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.viewBinding

class ChartFragment : BaseFragment(
    R.layout.fragment_chart
) {
    private val binding by viewBinding { FragmentChartBinding.bind(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTexts()
        var isBaseLinearVisible = false
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnFilterSearch.click {
            isBaseLinearVisible = !isBaseLinearVisible
            binding.baseLinear.isVisible = isBaseLinearVisible

            binding.btnFilterSearch.apply {
                text = if (isBaseLinearVisible) {
                    setBackColor(R.color.blue)
                    setTextColor(Color.WHITE)
                    getString(R.string.search_f)
                } else {
                    setBackColor(R.color.white)
                    setTextColor(Color.BLUE)
                    getText(R.string.filter)
                }
            }
        }
        setUpAutoComplete()
//        MaterialDatePicker.Builder.datePicker()
//            .setTitleText("")
//            .build()
//            .show(childFragmentManager, "DATE_PICKER")
    }

    private fun setUpAutoComplete() {
        var text = getString(R.string.process)
        var arrayAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_list_item_1,
            listOf(text)
        )
        binding.autoCompleteTv.setAdapter(arrayAdapter)
        binding.autoCompleteTv.click {
            text = if (text == getString(R.string.process)) getString(R.string.filter) else getString(R.string.process)
            arrayAdapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_list_item_1,
                listOf(text)
            )
            binding.autoCompleteTv.setAdapter(arrayAdapter)
        }
    }
    private fun editTexts() {
        binding.apply {
            val list = mapOf(
                etFromDate to tvDate,
                etToDate to tvToDate
            )
            list.forEach {
                it.key.sutUpInput(it.value)
            }
        }
    }
}