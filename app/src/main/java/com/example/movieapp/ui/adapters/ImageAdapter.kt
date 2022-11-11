package com.example.movieapp.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.squareup.picasso.Picasso

class ImageAdapter(
    c: Context,
//    images: List<String>,
) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {
    private var ls: List<String> = ArrayList()
    private var context: Context? = null

    init {
//        ls = images
        context = c
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView

        init {
            // get the reference of item view's
            image = itemView.findViewById<View>(R.id.imageIcon) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_grid_view, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val Imgurl = "https://image.tmdb.org/t/p/w500" + ls[position]
        Log.i("!@#","${ls.size}")
        Picasso.get().load(Imgurl).into(holder.image)

    }

    override fun getItemCount(): Int {
        return ls.size
    }
    fun submitlist(list:List<String>){
        Log.i("@@@@@","ADAPTER CALLEd")
        ls=list
        notifyDataSetChanged()
    }

}