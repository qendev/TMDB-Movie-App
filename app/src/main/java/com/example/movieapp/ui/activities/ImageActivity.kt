package com.example.movieapp.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.adapters.ImageAdapter

class ImageActivity: AppCompatActivity() {

    private var rv: RecyclerView? = null
    private var ls = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_activity)
        rv = findViewById<View>(R.id.recyclerview_image) as RecyclerView
        val intent = intent
        val c = ImageAdapter(this)


        val gridLayoutManager =
            GridLayoutManager(applicationContext, 3, RecyclerView.VERTICAL, false)
        rv!!.layoutManager = gridLayoutManager
        rv!!.adapter = c



    }

}