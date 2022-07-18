package com.example.movies2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movies2.databinding.ActivityMainBinding
import com.example.movies2.util.UIBehavior
import com.example.movies2.vews.fragment.HomeFragment
import com.example.movies2.vews.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UIBehavior {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initUI()
    }

    override fun initUI() {
        initBottomNavigationView()
        viewModel.onCreate()
    }

    private fun initBottomNavigationView() {
        val bottomNavigationView = binding.bottomNavigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                /*R.id.home_fragment ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, HomeFragment.instance)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.fav_fragment -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, RecommendedFragment.instance)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.tv_fragment -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, TvFragment.instance)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }*/
                else -> false
            }
        }

    }


}