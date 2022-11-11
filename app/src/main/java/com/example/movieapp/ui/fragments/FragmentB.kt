package com.example.movieapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentBBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.activities.DetailsActivity
import com.example.movieapp.ui.adapters.Trending_Adapter
import com.example.movieapp.viewmodel.MovieViewModel

class FragmentB(): Fragment(R.layout.fragment_b), Trending_Adapter.TrendingAdapterOnClickHandler {

    private var binding: FragmentBBinding? = null
    private lateinit var trendingadapter: Trending_Adapter
    private val viewmodel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        init_recyclerview()
        adddataset()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun init_recyclerview(){

    }

    private fun adddataset(){

    }

    override fun onClickT(m: Movie?) {

        val intentToStartDetailActivity = Intent(this.context, DetailsActivity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }




















}