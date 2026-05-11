package com.example.pixorawallpaperapp

import android.content.Intent
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WallpaperAdapter(private val list: List<String>) :
    RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {

    class ViewHolder(val image: ImageView) : RecyclerView.ViewHolder(image)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val image = ImageView(parent.context)

        image.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            500
        )

        image.scaleType = ImageView.ScaleType.CENTER_CROP
        image.setPadding(8, 8, 8, 8)

        return ViewHolder(image)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val imageUrl = list[position]

        Glide.with(holder.image.context)
            .load(imageUrl)
            .into(holder.image)

        holder.image.setOnClickListener {

            val intent = Intent(holder.image.context, FullScreenActivity::class.java)

            intent.putStringArrayListExtra("images", ArrayList(list))
            intent.putExtra("position", position)

            holder.image.context.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size
}