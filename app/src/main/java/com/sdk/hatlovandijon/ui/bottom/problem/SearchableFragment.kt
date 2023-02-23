package com.sdk.hatlovandijon.ui.bottom.problem

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sdk.domain.model.search.SearchData
import com.sdk.hatlovandijon.databinding.SearchableDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchableFragment : DialogFragment() {
    private val viewModel by viewModels<ProblemViewModel>()
    private var _binding: SearchableDialogBinding? = null
    private val binding get() = _binding!!
    private val searchList = mutableListOf<SearchData>()
    lateinit var selectedData: SearchData
    private var clickListener: ClickListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = SearchableDialogBinding.inflate(layoutInflater)
        return MaterialAlertDialogBuilder(requireContext()).setView(binding.root).create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.searchData.collect { list ->
                val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list.map { it.name })
                binding.listView.adapter = arrayAdapter
                searchList.clear()
                searchList.addAll(list)
            }
        }

        binding.etSearch.addTextChangedListener { edit ->
            edit?.let { text ->
                val s = searchList.filter { it.name.lowercase().contains(text.toString().lowercase()) }
                val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, s.map { it.name })
                binding.listView.adapter = arrayAdapter
            }
        }
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            selectedData = searchList[position]
            clickListener?.selectedItem(selectedData.name)
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            clickListener = targetFragment as ClickListener
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "SearchableFragment"
    }
    interface ClickListener {
        fun selectedItem(title: String)
    }
}