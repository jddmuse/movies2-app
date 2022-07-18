package com.example.movies2.vews.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movies2.data.model.baseUrlImg
import com.example.movies2.databinding.ActivityMovieDetailsBinding
import com.example.movies2.domain.model.Movie
import com.example.movies2.util.UIBehavior

class MovieDetailsActivity : AppCompatActivity(), UIBehavior {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initUI()
    }

    override fun initUI() {
        val item = intent?.getSerializableExtra("movie") as Movie
        with(binding){
            Glide.with(applicationContext).load("$baseUrlImg${item.backdrop_path}")
                .into(backdropImageView)
            Glide.with(applicationContext).load("$baseUrlImg${item.poster_path}")
                .into(posterImageView)
            titleTextView.text = item.title
            descriptionTextView.text = item.overview
            dateTextView.text = "Release: ${item.release_date}"
            popularityTextView.text = "Popularity: ${item.popularity}"
            averageTextView.text = "Average: ${item.vote_average}"
        }
    }
}