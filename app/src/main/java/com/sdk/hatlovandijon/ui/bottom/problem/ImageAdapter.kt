package com.sdk.hatlovandijon.ui.bottom.problem


import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sdk.hatlovandijon.databinding.ImageLayoutBinding

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    var uriList = mutableListOf<Uri>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(uriList[position])
    }

    override fun getItemCount(): Int = uriList.size

    inner class ImageViewHolder(private val binding: ImageLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(uri: Uri) {
            binding.imageView.setImageURI(uri)
        }
    }
    fun addList(uris: MutableList<Uri>){
        uriList = uris
        notifyDataSetChanged()
    }
}