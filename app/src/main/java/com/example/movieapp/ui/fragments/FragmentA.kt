package com.example.movieapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentABinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.activities.DetailsActivity
import com.example.movieapp.ui.adapters.*
import com.example.movieapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentA(): Fragment(R.layout.fragment_a), TopRatedAdapter.TopratedAdapterOnClickHandler,
NowPlayingAdapter.NowplayingAdapterOnClickHandler,
TrendingAdapter.TrendingAdapterOnClickHandler, PopularAdapter.PopularAdapterOnClickHandler,
UpcomingAdapter.UpcomingAdapterOnClickHandler , TvAdapter.Tv_AdapterOnclickHandler {

    private var binding: FragmentABinding? = null
    private lateinit var padapter: PopularAdapter
    private lateinit var uadapter: UpcomingAdapter
    private lateinit var tadapter: TopRatedAdapter
    private lateinit var nadapter: NowPlayingAdapter
    private lateinit var tvadapter: TvAdapter
    private lateinit var trendingadapter: TrendingAdapter
    private val viewmodel: MovieViewModel by viewModels()





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.i("cameyo","d")
        binding = FragmentABinding.inflate(inflater, container, false)
        val view = binding!!.root
        init_recyclerview()
        adddataset()
        return view
    }



    private fun init_recyclerview() {
        binding?.p?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            trendingadapter = TrendingAdapter(this@FragmentA)
            adapter = trendingadapter
        }

        binding?.u?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            padapter = PopularAdapter(this@FragmentA)
            adapter = padapter
        }
        binding?.u2?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            nadapter = NowPlayingAdapter(this@FragmentA)
            adapter = nadapter
        }

        binding?.u3?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            uadapter = UpcomingAdapter(this@FragmentA)
            adapter = uadapter
        }
        binding?.u4?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            tadapter = TopRatedAdapter(this@FragmentA)
            adapter = tadapter
        }
        binding?.u5?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
            tvadapter = TvAdapter(this@FragmentA)
            adapter = tvadapter
        }

    }

    private fun adddataset() {
        Log.i("@@@@@", "CALLIG ADAPTER")
        viewmodel._trending.observe(viewLifecycleOwner, { Movies ->
//            binding?.progress?.visibility=View.VISIBLE
            trendingadapter.submitlist(Movies)
            binding?.trending?.visibility = View.VISIBLE
            binding?.trending1?.visibility=View.VISIBLE
        })
        viewmodel._Popular.observe(viewLifecycleOwner, { Movies ->
            padapter.submitlist(Movies)
            binding?.popularT?.visibility = View.VISIBLE
            binding?.popular1?.visibility = View.VISIBLE

        })
        viewmodel._topRated.observe(viewLifecycleOwner, { Movies ->
            tadapter.setToprateddata(Movies)
            binding?.topratedT?.visibility = View.VISIBLE
        })
        viewmodel._upComing.observe(viewLifecycleOwner, { Movies ->
            uadapter.setUpcomingdata(Movies)
            binding?.upcomingT?.visibility = View.VISIBLE
        })
        viewmodel._nowPlaying.observe(viewLifecycleOwner, { Movies ->
            nadapter.submitlist(Movies)
            binding?.nowPlaying?.visibility = View.VISIBLE
        })
        viewmodel._tv.observe(viewLifecycleOwner, { Movies ->
            tvadapter.submitlist(Movies)
            binding?.tv?.visibility = View.VISIBLE
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        Log.i("detached", "dff")
    }

    override fun onClickt(m: Movie?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, DetailsActivity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

    override fun onClickN(m: Movie?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, DetailsActivity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

    override fun onClickT(m: Movie?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, DetailsActivity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

    override fun onClickp(m: Movie?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, DetailsActivity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

    override fun onClicku(m: Movie?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, DetailsActivity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }

    override fun onclick(m: Movie?) {
        Log.i("~~~~~~~~~~`", "s" + "clicked" + m?.Title)
        val intentToStartDetailActivity = Intent(this.context, DetailsActivity::class.java)
        intentToStartDetailActivity.putExtra("parcea", m)
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)
    }


}