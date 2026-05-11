package com.example.pixorawallpaperapp

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class FullScreenActivity : AppCompatActivity() {

    private lateinit var list: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val downloadBtn = findViewById<ImageButton>(R.id.downloadBtn)

        list = intent.getStringArrayListExtra("images") ?: arrayListOf()

        val position = intent.getIntExtra("position", 0)

        viewPager.adapter = ImagePagerAdapter(list)
        viewPager.setCurrentItem(position, false)

        downloadBtn.setOnClickListener {

            val imageUrl = list[viewPager.currentItem]

            val request = DownloadManager.Request(Uri.parse(imageUrl))

            request.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            )

            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "wallpaper_${System.currentTimeMillis()}.jpg"
            )

            val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)

            Toast.makeText(this, "Downloading...", Toast.LENGTH_SHORT).show()
        }
    }
}