package com.sdk.hatlovandijon.ui.bottom.chart

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentChartBinding
import com.sdk.hatlovandijon.ui.adapter.FilterAdapter
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.Constants.TAG
import com.sdk.hatlovandijon.util.viewBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class ChartFragment : BaseFragment(
    R.layout.fragment_chart
) {
    private val binding by viewBinding { FragmentChartBinding.bind(it) }
    private val filterAdapter by lazy { FilterAdapter() }
    private val viewModel: ChartViewModel by viewModels()
    private var type = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAutoComplete()
        setUpEditTexts()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnFilterSearch.click {
            binding.btnFilterSearch.isVisible = false
            binding.baseLinear.isVisible = true
            binding.btnSearch.isVisible = true
        }
        binding.btnSearch.click {
            filterAppeals()
        }
        binding.rv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = filterAdapter
        }
        filterAdapter.onClick = {
            val bundle = bundleOf("id" to it)
            findNavController().navigate(R.id.action_chartFragment_to_detailFragment, bundle)
        }
        binding.btnClear.click {
            binding.btnClear.isVisible = false
            binding.etFromDate.setText("")
            binding.etToDate.setText("")
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
        lifecycleScope.launchWhenResumed {
            viewModel.variableList.collect { list ->
                val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list.map { it.name })
                binding.autoCompleteTv.setAdapter(arrayAdapter)
                binding.autoCompleteTv.setOnItemClickListener { _, _, position, _ ->
                    type = list[position].id
                }
            }
        }
    }

    private fun filterAppeals() {
        Log.d(TAG, "filterAppeals: ${queryMap()}")
        lifecycleScope.launch {
            viewModel.filterAppeals(queryMap())
                .onStart {
                    binding.rv.isVisible = false
                    binding.shimmer.isVisible = true
                    binding.shimmer.startShimmer()
                    delay(800L)
                }
                .catch {
                    snack(getString(R.string.error_occ), false)
                }
                .collectLatest {
                    binding.shimmer.stopShimmer()
                    binding.shimmer.isVisible = false
                    binding.rv.isVisible = true
                filterAdapter.submitData(it)
            }
        }
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(getString(R.string.date_enter))
            .setNegativeButtonText(getText(R.string.cancel))
            .setPositiveButtonText(getText(R.string.set))
            .build()

        datePicker.addOnPositiveButtonClickListener {
            binding.btnClear.isVisible = true
            val timeZoneUTC: TimeZone = TimeZone.getDefault()
            val offsetFromUTC: Int = timeZoneUTC.getOffset(Date().time) * -1
            val simpleFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date1 = Date(it.first + offsetFromUTC)
            val date2 = Date(it.second + offsetFromUTC)
            binding.etFromDate.setText(simpleFormat.format(date1))
            binding.etToDate.setText(simpleFormat.format(date2))
        }
        datePicker.show(childFragmentManager, "Date_Range")
    }
    private fun queryMap(): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()
        val fromDate = binding.etFromDate.text.toString().trim()
        val toDate = binding.etToDate.text.toString().trim()
        if (fromDate.isNotBlank()) {
            hashMap["date_start"] = fromDate
        }
        if (toDate.isNotBlank()) {
            hashMap["date_end"] = toDate
        }
        hashMap["turi"] = type
        return hashMap
    }
}