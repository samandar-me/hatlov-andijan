package com.sdk.hatlovandijon.ui.bottom.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.FragmentDetailBinding
import com.sdk.hatlovandijon.ui.base.BaseFragment
import com.sdk.hatlovandijon.util.viewBinding
import java.util.Calendar


class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val binding by viewBinding { FragmentDetailBinding.bind(it) }
    private var appeal: Murojaatlar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appeal = arguments?.getSerializable("appeal") as? Murojaatlar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
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
    }
}