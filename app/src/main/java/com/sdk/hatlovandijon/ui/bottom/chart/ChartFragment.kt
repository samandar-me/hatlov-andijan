package com.sdk.hatlovandijon.ui.bottom.chart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentChartBinding
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.viewBinding
import java.text.SimpleDateFormat
import java.util.*

class ChartFragment : BaseFragment(
    R.layout.fragment_chart
) {
    private val binding by viewBinding { FragmentChartBinding.bind(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAutoComplete()
        setUpEditTexts()
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
    }

    private fun setUpEditTexts() {
        binding.apply {
            etFromDate.setOnFocusChangeListener { _, focus ->
                if (focus) {
                    showDatePicker()
                    tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
                } else {
                    tvDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            etToDate.setOnFocusChangeListener { _, focus ->
                if (focus) {
                    showDatePicker()
                    tvToDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
                } else {
                    tvToDate.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
        }
    }

    private fun setUpAutoComplete() {
        var text = getString(R.string.process)
        var arrayAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_list_item_1,
            listOf(text)
        )
        binding.autoCompleteTv.setAdapter(arrayAdapter)
        binding.autoCompleteTv.click {
            text =
                if (text == getString(R.string.process)) getString(R.string.completed) else getString(R.string.process)
            arrayAdapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_list_item_1,
                listOf(text)
            )
            binding.autoCompleteTv.setAdapter(arrayAdapter)
        }
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(getString(R.string.date_enter))
            .setNegativeButtonText(getText(R.string.cancel))
            .setPositiveButtonText(getText(R.string.set))
            .build()

        datePicker.addOnPositiveButtonClickListener {
            val timeZoneUTC: TimeZone = TimeZone.getDefault()
            val offsetFromUTC: Int = timeZoneUTC.getOffset(Date().time) * -1
            val simpleFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val date1 = Date(it.first + offsetFromUTC)
            val date2 = Date(it.second + offsetFromUTC)
            binding.etFromDate.setText(simpleFormat.format(date1))
            binding.etToDate.setText(simpleFormat.format(date2))
        }
        datePicker.show(childFragmentManager, "Date_Range")
    }
}