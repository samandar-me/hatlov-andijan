package com.sdk.hatlovandijon.ui.bottom.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentDetailBinding
import com.sdk.hatlovandijon.ui.adapter.DetailImageAdapter
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.viewBinding
import kotlinx.coroutines.launch
import java.util.Calendar


class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val binding by viewBinding { FragmentDetailBinding.bind(it) }
    private var appeal: Murojaatlar? = null
    private val detailImageAdapter by lazy { DetailImageAdapter() }
    private val viewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appeal = arguments?.getSerializable("appeal") as? Murojaatlar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.rv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = detailImageAdapter
        }
        binding.linearLocation.click {

        }
        binding.linearNumber.click {
            appeal?.let {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("tel:${it.owner_home_phone}")
                startActivity(intent)
            }
        }
        binding.apply {
            appeal?.let {
                viewModel.onEvent(DetailEvent.OnGetDetailImages(it.id))
                tvFullName.text = it.owner_home_name
                toolbar.title = it.owner_home_name
                toolbar.subtitle = it.mahalla
                val year = Calendar.getInstance().get(Calendar.YEAR)
                tvAge.text = (year - it.owner_home_year).toString()
                cardView.setCardBackgroundColor(Color.parseColor(it.turi.color))
                cardTv.text = it.izoh
                tvBtn.text = it.status.name
            }
        }
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is DetailState.Loading -> Unit
                    is DetailState.Error -> {
                        snack(it.message, false)
                    }
                    is DetailState.Success -> {
                        binding.pr.isVisible = false
                        detailImageAdapter.submitList(it.images)
                    }
                }
            }
        }
    }
}