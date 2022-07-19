package com.example.movies2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.movies2.databinding.ActivityMainBinding
import com.example.movies2.domain.model.Movie
import com.example.movies2.util.ItemActionListener
import com.example.movies2.util.UIBehavior
import com.example.movies2.vews.activity.MovieDetailsActivity
import com.example.movies2.vews.adapter.HeaderMoviesViewPagerAdapter
import com.example.movies2.vews.adapter.MoviesListsAdapter
import com.example.movies2.vews.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UIBehavior, UIBehavior.RecyclerView, UIBehavior.ViewPager,
    ItemActionListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    // binding
    private lateinit var binding: ActivityMainBinding

    // viewModel
    private val viewModel: MainViewModel by viewModels()

    // adapter
    private val generalMoviesListAdapter = MoviesListsAdapter(this)
    private val headerAdapter = HeaderMoviesViewPagerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initUI()
    }

    override fun initUI() {
        initRecyclerView()
        initViewPager2()
        initLiveDataObservables()
        viewModel.onCreate()
    }

    private fun initLiveDataObservables() {
        viewModel.generalMoviesLists.observe(this, Observer {
            Log.i(TAG, "CHANGE OBSERVED viewModel.generalMoviesLists")
            generalMoviesListAdapter.onUpdateData(it!!)
            headerAdapter.onUpdateData(it.random().results)
        })
    }

    override fun initRecyclerView() {
        with(binding) {
            rvGeners.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            rvGeners.adapter = generalMoviesListAdapter
        }
    }

    override fun initViewPager2() {
        val viewPager = binding.headerViewPager
        viewPager.adapter = headerAdapter

        val handler = Handler()
        // AUTO-SCROLL: viewPager2
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val runnable = Runnable {
                    viewPager.currentItem = position + 1
                }
                if (position < viewPager.adapter?.itemCount ?: 0)
                    handler.postDelayed(runnable, 5000)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == ViewPager2.SCROLL_STATE_DRAGGING)
                    handler.removeMessages(0)
            }
        })
    }

    override fun onClickItem(item: Any, position: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra("movie", item as Movie)
        }
        startActivity(intent)
    }

}