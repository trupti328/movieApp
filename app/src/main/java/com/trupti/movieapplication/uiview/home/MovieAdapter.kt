package com.trupti.movieapplication.uiview.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trupti.movieapplication.R
import com.trupti.movieapplication.model.Movie

class MovieAdapter(
    private var movies: List<Movie>,
    private val onMovieClick: (Int) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitleTextView: TextView = itemView.findViewById(R.id.tvMovieName)

        fun bind(movie: Movie, onMovieClick: (Int) -> Unit) {
            movieTitleTextView.text = movie.title
            itemView.setOnClickListener {
                onMovieClick(movie.id)

                Log.d("MovieAdapter", "Clicked movie: ${movie}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movies, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onMovieClick)
    }

    override fun getItemCount(): Int = movies.size

//    fun updateList(newList: List<Movie>) {
//        movies = newList
//        notifyDataSetChanged()
//    }
}


