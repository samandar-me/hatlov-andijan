package com.sdk.hatlovandijon.ui.adapter

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.sdk.domain.model.detail.Image
import com.sdk.hatlovandijon.databinding.DetailImageItemBinding
import com.sdk.hatlovandijon.databinding.ProblemItemBinding
import com.sdk.hatlovandijon.util.Constants

class DetailImageAdapter :
    ListAdapter<Image, DetailImageAdapter.DetailImageViewHolder>(DiffCallBack()) {
    private class DiffCallBack : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImageViewHolder {
        return DetailImageViewHolder(
            DetailImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DetailImageViewHolder(private val binding: DetailImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            binding.imv.apply {
                Glide.with(this)
                    .load("${Constants.BASE_URL}${image.image}")
                    .addListener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.isVisible = false
                            return false
                        }
                    })
                    .into(this)
            }
        }
    }
}