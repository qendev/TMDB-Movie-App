package com.example.movieapp.ui.activities

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityDetailsBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.adapters.TrendingAdapter
import com.example.movieapp.viewmodel.MovieDetailsViewModel
import com.example.movieapp.viewmodel.SimilarMoviesViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

@AndroidEntryPoint
class DetailsActivity: AppCompatActivity(), TrendingAdapter.TrendingAdapterOnClickHandler{

    private val list: List<String> = ArrayList()
    var Titled: String? = null
    var Imaged: String? = null
    var Ratingd: String? = null
    var backimaged: String? = null
    var infod: String? = null
    var movieidd: Int? = null
    private lateinit var binding: ActivityDetailsBinding
    lateinit var viewmodel: MovieDetailsViewModel
    private var flag: Int? = null
    private lateinit var t1: Toast
    private lateinit var trendingadapter: TrendingAdapter
    private val viewmodels: SimilarMoviesViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intentthatstartedthisactivity: Intent = intent
        val m: Movie? = intentthatstartedthisactivity.getParcelableExtra("parcea")
        Titled = m?.Title
        Imaged = m?.Poster
        Ratingd = m?.Vote
        backimaged = m?.BackImage
        infod = m?.Overview
        movieidd = m?.Movieid
        Log.i("idbhai", "${movieidd}")
        viewmodel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        t1 = Toast.makeText(this,
            "Sorry not available for now",
            Toast.LENGTH_SHORT)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)



        setWatchlistState();
        setImages()
        setInfo()
        setWatchList()
        setSimilarMovies()
        binding.TitleFr.setText(Titled)
        binding.ratingFr.text = Ratingd



    }

    private fun setWatchlistState() {
        GlobalScope.launch(Dispatchers.IO) {
            flag = viewmodel.getflagfromtitle(Titled)
            if (flag == 0) {
                runOnUiThread {
                    binding.watchlistFr.text = "Add to watchlist"
                    binding.watchlistFr.setTextColor(resources.getColor(R.color.white))
                }
            } else {
                runOnUiThread {
                    binding.watchlistFr.text = "Added to watchlist"
                    binding.watchlistFr.setTextColor(resources.getColor(R.color.colorPrimary))
                }
            }
        }
    }

    private fun setImages() {
        val url = "https://image.tmdb.org/t/p/w500" + Imaged
        Picasso.get().load(url).resize(180, 260).into(binding.posterFr)

        val title = "https://image.tmdb.org/t/p/w500" + backimaged
        Picasso.get().load(title).resize(1610, 810).into(binding.backgroundImageFr)
    }

    private fun setInfo() {
        var overview = infod
        var extra: String? = null
        if (overview != null) {
            if (overview.length > 90) {
                extra = overview.substring(90, overview.length)
                overview = overview.substring(0, 90) + "..."
            }
            binding.overviewFr.setText(overview)
        }
        if (extra != null) {
            binding.moreFr.setOnClickListener {
                if (binding.moreFr.text.toString().equals("More")) {
                    binding.overviewFr.setText(overview?.substring(0, 90) + extra)
                    binding.moreFr.text = "Less"
                } else {
                    binding.overviewFr.setText(overview)
                    binding.moreFr.text = "More"
                }
            }
        } else {
            binding.moreFr.visibility = View.INVISIBLE
        }
    }

    private fun setWatchList() {
        binding.watchlistFr.setOnClickListener {
            if (binding.watchlistFr.currentTextColor == resources.getColor(R.color.white)) {
                viewmodel.addMovie(Movie(flag = 1,
                    Movieid = movieidd,
                    Title = Titled,
                    Vote = Ratingd,
                    Overview = infod,
                    BackImage = backimaged,
                    Poster = Imaged))
                binding.watchlistFr.text = "Added To watchlist"
                binding.watchlistFr.setTextColor(resources.getColor(R.color.colorPrimary))
            } else {
                viewmodel.deleteMovieWithTitle(Titled)
                binding.watchlistFr.text = "Add To watchlist"
                binding.watchlistFr.setTextColor(resources.getColor(R.color.white))
            }
        }
    }

    private fun setSimilarMovies(){
        binding.recyclerviewSimilar.apply {
            layoutManager =
                LinearLayoutManager(this@DetailsActivity, RecyclerView.HORIZONTAL, false)
            trendingadapter = TrendingAdapter(this@DetailsActivity)
            adapter = trendingadapter
        }
        viewmodels.getSimilarMovies(movieidd)
        viewmodels.similarMovies.observe(this) { Movies ->
            if (Movies.size != 0) {
                Log.i("response", "${Movies?.get(0)?.Title}")
                trendingadapter.submitlist(Movies)

            } else {
                binding.similarTextview.visibility = View.GONE
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.nothing, R.anim.slide_out)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem?.actionView as SearchView
        val cn = ComponentName(this, SearchActivity::class.java)
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(cn))
        Log.i("@!@!", "" + searchManager.getSearchableInfo(cn) + "f")
        return super.onCreateOptionsMenu(menu)

    }




    override fun onClickT(m: Movie?) {
        val intentToStartDetailActivity = Intent(this, DetailsActivity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

}


















