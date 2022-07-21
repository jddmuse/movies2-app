package com.example.movies2.vews.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies2.R
import com.example.movies2.databinding.ItemMovieSearchBinding
import com.example.movies2.domain.model.Movie
import com.example.movies2.util.ItemActionListener

class MoviesSearchAdapter(
    val itemActionListener: ItemActionListener
): RecyclerView.Adapter<MoviesSearchAdapter.ViewHolder>() {

    companion object {
        private const val TAG = "MoviesSearchAdapter"
    }

    private val list = arrayListOf<Movie>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesSearchAdapter.ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie_search, parent, false
        )
    )

    override fun onBindViewHolder(holder: MoviesSearchAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemActionListener.onClickItem(item, position)
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemMovieSearchBinding.bind(view)

        fun bind(item: Movie){
            binding.textView.setText(item.title)
        }
    }

    fun onUpdateData(items: List<Movie>) {
        list.clear()
        list.addAll(items)
        Log.d(TAG, "METHOD CALLED: updateData()")
        notifyDataSetChanged()
    }

}