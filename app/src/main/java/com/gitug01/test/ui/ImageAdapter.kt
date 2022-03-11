package com.gitug01.test.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gitug01.test.R
import com.gitug01.test.domain.ImageEntity
import com.gitug01.test.ui.screens.OnImageClickListener

class ImageAdapter(
    private var onImageClickListener: OnImageClickListener
) : RecyclerView.Adapter<ImageViewHolder>() {
    private var data: List<ImageEntity>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ImageEntity>) {
        this.data = ArrayList(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_card, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageEntity = getitem(position)
        holder.imageView.setImageResource(R.drawable.ic_launcher_background)
        Glide.with(holder.imageView).load(imageEntity.imagePath).into(holder.imageView)

        holder.imageView.setOnClickListener { onImageClickListener.onClick(getitem(position).imagePath) }
    }

    private fun getitem(position: Int) = data!![position]

    override fun getItemCount(): Int {
        return data!!.size
    }
}