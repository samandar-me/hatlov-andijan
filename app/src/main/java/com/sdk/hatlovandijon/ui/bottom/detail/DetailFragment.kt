package com.sdk.hatlovandijon.ui.bottom.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.domain.model.detail.Data
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentDetailBinding
import com.sdk.hatlovandijon.ui.adapter.DetailImageAdapter
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.Constants.TAG
import com.sdk.hatlovandijon.util.splitText
import com.sdk.hatlovandijon.util.viewBinding
import kotlinx.coroutines.launch
import java.util.*


class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val binding by viewBinding { FragmentDetailBinding.bind(it) }
    private var id: Int? = null
    private val detailImageAdapter by lazy { DetailImageAdapter() }
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var data: Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getInt("id")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id?.let {
            viewModel.onEvent(DetailEvent.OnGetDetailData(it))
        }
        setupRv()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnEdit.click {
            if (::data.isInitialized) {
                val bundle = bundleOf("data" to data)
                findNavController().navigate(R.id.action_detailFragment_to_editAppealFragment, bundle)
            }
        }
        observeState()
    }
    private fun setupRv() {
        val compositeTrans = CompositePageTransformer()
        compositeTrans.addTransformer(MarginPageTransformer(0))
        compositeTrans.addTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.27f
        }
        binding.viewPager.apply {
            adapter = detailImageAdapter
            offscreenPageLimit = 3
            clipToPadding = true
            clipChildren = true
            setPageTransformer(compositeTrans)
        }
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
                        detailImageAdapter.submitList(it.detailData.data.images)
                        showUI(it.detailData.data)
                    }
                }
            }
        }
    }

    private fun showUI(data: Data) {
        this.data = data
        binding.apply {
            toolbar.title = data.owner_home_name.splitText()
            tvFullName.text = data.owner_home_name.splitText()
            tvAge.text = data.owner_home_year.toString().splitText()
            tvGen.text = data.owner_home_jinsi.splitText()
            cardTv.text = data.izoh.splitText()
            tvLoc.text = data.address.splitText()
            tvPhone.text = data.owner_home_phone.splitText()
            cardView.setCardBackgroundColor(Color.parseColor(data.turi.color))
            tvBtn.text = data.status.name
            tvDeadLine.text = data.deadline

            binding.linearNumber.click {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("tel:$${data.owner_home_phone.splitText()}")
                startActivity(intent)
            }
        }
    }
}