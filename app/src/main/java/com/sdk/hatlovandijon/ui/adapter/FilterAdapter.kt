package com.sdk.hatlovandijon.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.hatlovandijon.databinding.ProblemItemBinding
import com.sdk.hatlovandijon.util.splitText

class FilterAdapter: PagingDataAdapter<Murojaatlar, FilterAdapter.FilterViewHolder>(DiffCallBack()) {
    lateinit var onClick: (Int) -> Unit
    private class DiffCallBack: DiffUtil.ItemCallback<Murojaatlar>() {
        override fun areItemsTheSame(oldItem: Murojaatlar, newItem: Murojaatlar): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Murojaatlar, newItem: Murojaatlar): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            ProblemItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class FilterViewHolder(private val binding: ProblemItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(murojaat: Murojaatlar) {
            binding.apply {
                tvName.text = murojaat.owner_home_name.splitText()
                textPro.text = murojaat.status.name.splitText()
                cardView.setCardBackgroundColor(Color.parseColor(murojaat.turi.color))
                textProblem.text = murojaat.izoh.splitText()
                textAddress.text = murojaat.address.splitText()
                textTime.text = murojaat.deadline.splitText()
            }
            itemView.setOnClickListener {
                onClick(murojaat.id)
            }
        }
    }
}