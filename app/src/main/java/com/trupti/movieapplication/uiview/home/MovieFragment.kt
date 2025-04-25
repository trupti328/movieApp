package com.trupti.movieapplication.uiview.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.trupti.movieapplication.R
import com.trupti.movieapplication.model.Movie
import com.trupti.movieapplication.network.ApiService
import com.trupti.movieapplication.network.RetrofitInstance

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import org.koin.android.ext.android.inject


class MovieFragment : Fragment() {
    private lateinit var shimmerLayout: ShimmerFrameLayout
    private lateinit var recyclerView: RecyclerView
    private val apiKey = "aDBJLZnJZ6DLwIjL7wPFjJs0Y3qsvr41HkF9OIix"




    private val apiService: ApiService by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)


        // Initialize the views
        shimmerLayout = view.findViewById(R.id.shimmerLayout)
        recyclerView = view.findViewById(R.id.recyclerView)

        // Set up RecyclerView layout manager
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Start shimmer effect
        shimmerLayout.startShimmer()

        // Fetch movies
        fetchMovies()

        return view
    }

    private fun fetchMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Make API call to fetch movies
                val response = apiService.getMovies(apiKey)

                if (response.isSuccessful) {
                    val movies: List<Movie> = response.body()?.titles ?: listOf()

                    withContext(Dispatchers.Main) {

                        recyclerView.adapter = MovieAdapter(movies)


                        shimmerLayout.stopShimmer()
                        shimmerLayout.visibility = View.GONE
                    }
                } else {
                    Log.e("API", "Failed: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error: ${e.message}")
            }
        }
    }
}