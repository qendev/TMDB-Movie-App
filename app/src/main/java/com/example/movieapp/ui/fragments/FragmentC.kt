package com.example.movieapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentCBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.activities.DetailsActivity
import com.example.movieapp.ui.adapters.Fragment_c_Adapter
import com.example.movieapp.viewmodel.MovieDetailsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentC: Fragment(R.layout.fragment_c), Fragment_c_Adapter.FragmentcOnClickHandler {

    private var binding: FragmentCBinding? = null
    private lateinit var padapter: Fragment_c_Adapter
    lateinit var viewmodel: MovieDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentCBinding.inflate(inflater, container, false)
        val view = binding!!.root

        viewmodel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        init_recyclerview()
        adddataset()

        return view
    }

    private fun init_recyclerview() {
        binding?.recyclerviewFragmentc?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            padapter = Fragment_c_Adapter(this@FragmentC)
            adapter = padapter
        }
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

                //Remove swiped item from list and notify the RecyclerView
                val position = viewHolder.adapterPosition

                GlobalScope.launch {
                    viewmodel.delete(padapter.getmovie().get(position))

                }
                Toast.makeText(activity?.applicationContext, "Deleted", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding?.recyclerviewFragmentc)

    }

    private fun adddataset() {
        viewmodel.allmovies.observe(viewLifecycleOwner) {
            padapter.submitlist(it)
        }
    }


    override fun onClickp(m: Movie?) {
        val intentToStartDetailActivity = Intent(this.context, DetailsActivity::class.java);
        intentToStartDetailActivity.putExtra("parcea", m);
        startActivity(intentToStartDetailActivity)
        activity?.overridePendingTransition(R.anim.slide_in, R.anim.nothing)

    }
}