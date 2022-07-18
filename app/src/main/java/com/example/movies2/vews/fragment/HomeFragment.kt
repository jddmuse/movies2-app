package com.example.movies2.vews.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.movies2.databinding.FragmentHomeBinding
import com.example.movies2.domain.model.Movie
import com.example.movies2.util.ItemActionListener
import com.example.movies2.util.UIBehavior
import com.example.movies2.vews.activity.MovieDetailsActivity
import com.example.movies2.vews.adapter.HeaderMoviesViewPagerAdapter
import com.example.movies2.vews.adapter.MoviesListsAdapter
import com.example.movies2.vews.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment(), UIBehavior, UIBehavior.RecyclerView, UIBehavior.ViewPager,
    ItemActionListener {

    // binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // viewModel
    private val viewModel: MainViewModel by viewModels()

    // adapter
    private val generalMoviesListAdapter = MoviesListsAdapter(this)
    private val headerAdapter = HeaderMoviesViewPagerAdapter()

    companion object {
        val instance = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun initUI() {
        initRecyclerView()
        initViewPager2()
        initLiveDataObservables()
        viewModel.onCreate()
    }

    private fun initLiveDataObservables() {
        viewModel.generalMoviesLists.observe(viewLifecycleOwner, Observer {
            Log.i(TAG, "CHANGE OBSERVED viewModel.generalMoviesLists")
            generalMoviesListAdapter.onUpdateData(it!!)
            headerAdapter.onUpdateData(it.random().results)
        })
    }

    override fun initRecyclerView() {
        with(binding) {
            rvGeners.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
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
        val intent = Intent(context, MovieDetailsActivity::class.java).apply {
            putExtra("movie", item as Movie)
        }
        startActivity(intent)
    }

}