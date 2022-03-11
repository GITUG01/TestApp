package com.gitug01.test.ui

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gitug01.test.R

class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.card_image)
}