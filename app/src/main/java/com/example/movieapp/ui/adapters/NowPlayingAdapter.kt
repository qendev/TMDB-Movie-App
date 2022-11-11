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

class NowPlayingAdapter(
    clickhandler: NowplayingAdapterOnClickHandler
) : RecyclerView.Adapter<NowPlayingAdapter.custoviewgolder>() {
    private var items: List<Movie> = ArrayList()
    private var mClickHandler: NowplayingAdapterOnClickHandler? = null
    init{
        mClickHandler=clickhandler
    }
    interface NowplayingAdapterOnClickHandler {
        fun onClickN(m: Movie?)
    }

    override fun getItemCount(): Int {
        Log.i("@@@@@","GET ITEM COUNT "+items.size)
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): custoviewgolder {
        Log.i("@@@@@","ON CREATE VIEW HOLDER")
        return custoviewgolder(
            LayoutInflater.from(parent.context).inflate(R.layout.now_playing,parent,false)
        )
    }
    fun submitlist(list:List<Movie>){
        Log.i("@@@@@","ADAPTER CALLEd")
        items=list
        notifyDataSetChanged()
    }

    inner class custoviewgolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val blog_image: ImageView = itemView.findViewById<ImageView>(R.id.blog_image_n)
        val blog_title: TextView = itemView.findViewById<View>(R.id.Title_n) as TextView
        val blog_rate: TextView = itemView.findViewById<View>(R.id.rate) as TextView

        fun bind(blogpost: Movie) {
            Log.i("@@@@@","BINDING VIEW HOLDER")
            blog_title.setText(blogpost.Title)
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+blogpost.Poster).into(blog_image)
            blog_rate.setText(blogpost.Vote)
        }

        override fun onClick(v: View?) {
            mClickHandler?.onClickN(items.get(adapterPosition))
        }
        init{
            itemView.setOnClickListener(this)
        }
    }

    override fun onBindViewHolder(holder: custoviewgolder, position: Int) {
        when (holder){
            is custoviewgolder ->{
                Log.i("@@@@@","ON BIND VIEW HOLDER")
                holder.bind(items.get(position))
            }
        }
    }
}