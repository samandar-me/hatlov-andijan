package com.sdk.hatlovandijon.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.hatlovandijon.databinding.ProblemItemBinding

class AppealAdapter: ListAdapter<Murojaatlar, AppealAdapter.AppealViewHolder>(DiffCallBack()) {
    lateinit var onClick: (Murojaatlar) -> Unit
    private class DiffCallBack: DiffUtil.ItemCallback<Murojaatlar>() {
        override fun areItemsTheSame(oldItem: Murojaatlar, newItem: Murojaatlar): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Murojaatlar, newItem: Murojaatlar): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppealViewHolder {
        return AppealViewHolder(
            ProblemItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AppealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AppealViewHolder(private val binding: ProblemItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(murojaat: Murojaatlar) {
            binding.apply {
                tvName.text = murojaat.owner_home_name
                textPro.text = murojaat.status.name
                cardView.setCardBackgroundColor(Color.parseColor(murojaat.turi.color))
                textProblem.text = murojaat.izoh
                textAddress.text = murojaat.address
                textTime.text = murojaat.deadline
            }
            itemView.setOnClickListener {
                onClick(murojaat)
            }
        }
    }
}