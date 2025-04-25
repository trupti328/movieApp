package com.trupti.movieapplication.uiview.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trupti.movieapplication.R
import com.trupti.movieapplication.model.Movie

class MovieAdapter(private val movies : List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.tvMovieId)
        val nameTextView: TextView = itemView.findViewById(R.id.tvMovieName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movies, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.idTextView.text = "ID: ${movie.id}"
        holder.nameTextView.text = "Name: ${movie.title}"
    }

    override fun getItemCount(): Int = movies.size

}