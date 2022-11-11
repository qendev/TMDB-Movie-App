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

class Fragment_c_Adapter(private var clickhandler: FragmentcOnClickHandler) : RecyclerView.Adapter<Fragment_c_Adapter.customviewholderF>() {
    private var items: List<Movie> = ArrayList()
    private var mClickHandler: FragmentcOnClickHandler? = null

    init {
        mClickHandler=clickhandler
    }

    interface FragmentcOnClickHandler {
        fun onClickp(m: Movie?)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customviewholderF {
        Log.i("@@@@@", "ON CREATE VIEW HOLDER")
        return customviewholderF(
            LayoutInflater.from(parent.context).inflate(R.layout.search, parent, false)
        )
    }
    fun submitlist(list: List<Movie>){
        Log.i("@@@@@", "ADAPTER CALLEd")
        items=list
        notifyDataSetChanged()
    }

    inner class customviewholderF constructor(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {
        val blog_image: ImageView = itemView.findViewById<ImageView>(R.id.imageview_movi)
        val blog_title: TextView = itemView.findViewById<View>(R.id.textview_titl) as TextView
        val blog_writer: TextView = itemView.findViewById<View>(R.id.textview_Write) as TextView

        fun bind(blogpost: Movie) {
            Log.i("@@@@@", "BINDING VIEW HOLDER")
            blog_title.setText(blogpost.Title)
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + blogpost.Poster).
            resize(450,450).placeholder(R.drawable.ic_launcher_background).into(
                blog_image)
            blog_writer.setText(blogpost.Vote)
        }

        override fun onClick(v: View?) {
            mClickHandler?.onClickp(items.get(adapterPosition))
        }

        init{
            itemView.setOnClickListener(this)
        }

    }

    fun getmovie(): List<Movie> {
        return items
    }
    override fun onBindViewHolder(holder: customviewholderF, position: Int) {
        when (holder){
            else -> {
                Log.i("@@@@@", "ON BIND VIEW HOLDER")
                holder.bind(items.get(position))
            }
        }
    }
}