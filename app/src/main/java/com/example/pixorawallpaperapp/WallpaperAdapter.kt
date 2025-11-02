package com.example.pixorawallpaperapp

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide

class WallpaperAdapter(private val list: List<String>) :
    Adapter<WallpaperAdapter.ViewHolder>() {

    class ViewHolder(val image: ImageView) : RecyclerView.ViewHolder(image)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val image = ImageView(parent.context)
        image.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            500
        )
        image.scaleType = ImageView.ScaleType.CENTER_CROP
        image.setPadding(4, 4, 4, 4)
        return ViewHolder(image)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.image.context)
            .load(list[position])
            .into(holder.image)
    }

    override fun getItemCount() = list.size
}
