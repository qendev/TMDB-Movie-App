package com.example.movieapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.squareup.picasso.Picasso

class Tv_Adapter(
    private var clickhandler: Tv_AdapterOnclickHandler,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Movie> = ArrayList()
    private var mclickhandler:Tv_AdapterOnclickHandler?=null

    init{
        mclickhandler=clickhandler
    }
    interface Tv_AdapterOnclickHandler {
        fun onclick(m:Movie?)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is customviewholder -> {
                Log.i("@@@@@", "ON BIND VIEW HOLDER")
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        Log.i("@@@@@", "GET ITEM COUNT " + items.size)
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.i("@@@@@", "ON CREATE VIEW HOLDER")
        return customviewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.tv_xml, parent, false)
        )
    }

    fun submitlist(list: List<Movie>) {
        Log.i("@@@@@", "ADAPTER CALLEd")
        items = list
        notifyDataSetChanged()
    }

    inner class customviewholder constructor(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val blog_image: ImageView = itemView.findViewById<ImageView>(R.id.blog_image_l)
        val blog_title: TextView = itemView.findViewById<View>(R.id.Title_l) as TextView
        val blog_rate: TextView = itemView.findViewById<View>(R.id.rate) as TextView

        fun bind(blogpost: Movie) {
            Log.i("@@@@@", "BINDING VIEW HOLDER")
            blog_title.setText(blogpost.Title)
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + blogpost.Poster).into(blog_image)
            blog_rate.setText(blogpost.Vote)
        }

        override fun onClick(v: View?) {
            mclickhandler?.onclick(items.get(adapterPosition))
        }
        init{
            itemView.setOnClickListener(this)
        }
    }
}