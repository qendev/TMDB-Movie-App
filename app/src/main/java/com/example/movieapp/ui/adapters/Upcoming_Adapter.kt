package com.example.movieapp.ui.adapters

import android.content.Context
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

class Upcoming_Adapter(
    private var clickhandler: UpcomingAdapterOnClickHandler,
) :
    RecyclerView.Adapter<Upcoming_Adapter.UpcomingAdapterViewHolder>() {
    private var movies: List<Movie>? = null
    private var mClickHandler: UpcomingAdapterOnClickHandler? = null

    init {
        mClickHandler = clickhandler
    }

    interface UpcomingAdapterOnClickHandler {
        fun onClicku(m: Movie?)
    }


    inner class UpcomingAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var image: ImageView? = null
        var Titl: TextView? = null
        var rate: TextView? = null
        override fun onClick(v: View) {
            val adapterPosition = adapterPosition
            mClickHandler?.onClicku(movies?.get(adapterPosition))
        }

        init {
            Log.i("----------", "ViewHolderConstructor")
            Titl = itemView.findViewById<View>(R.id.Tite) as TextView
            image = itemView.findViewById<View>(R.id.movie_imae) as ImageView
            rate=itemView.findViewById<TextView>(R.id.rate)
            itemView.setOnClickListener(this)
        }
    }

    override fun getItemCount(): Int {
        if (movies == null) {
            return 0
        }
        return movies!!.size
    }

    override fun onBindViewHolder(holder: UpcomingAdapterViewHolder, position: Int) {
        val Title: String? = movies?.get(position)?.Title
        Log.i("QQQQQ", "" + Title)
        holder.Titl!!.text = Title
        val Imgurl =
            "https://image.tmdb.org/t/p/w500" + movies?.get(position)?.Poster
        holder.rate!!.text=movies?.get(position)?.Vote
        if (movies?.get(position)?.Poster != null) {
            Picasso.get().load(Imgurl).resize(300, 420).into(holder.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingAdapterViewHolder {
        Log.i("@@", "hmm")

        val context: Context = parent.context
        val layoutIdForListItem: Int = R.layout.upcoming
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
        return UpcomingAdapterViewHolder(view)
    }

    fun setUpcomingdata(Upcomingdata: List<Movie>?) {
        Log.i("camebruh", "d" + Upcomingdata?.get(0)?.Title)
        movies = Upcomingdata
        notifyDataSetChanged()
    }
}