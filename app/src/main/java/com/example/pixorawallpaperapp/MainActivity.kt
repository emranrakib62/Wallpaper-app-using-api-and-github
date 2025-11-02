package com.example.pixorawallpaperapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.wallpaperRecycler)
        recycler.layoutManager = GridLayoutManager(this, 3) // 3 column grid

        val url = "https://raw.githubusercontent.com/USERNAME/wallpaper-api/main/wallpapers.json"

        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val json = JSONObject(response)
                val array = json.getJSONArray("wallpapers")

                val list = ArrayList<String>()
                for (i in 0 until array.length()) list.add(array.getString(i))

                recycler.adapter = WallpaperAdapter(list)
            },
            { Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show() })

        Volley.newRequestQueue(this).add(request)
    }
}
