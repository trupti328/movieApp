package com.trupti.movieapplication.uiview.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.trupti.movieapplication.R
import com.trupti.movieapplication.model.Movie
import com.trupti.movieapplication.network.ApiService
import com.trupti.movieapplication.network.RetrofitInstance
import com.trupti.movieapplication.uiview.detail.DetailsActivity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import org.koin.android.ext.android.inject


class MovieFragment : Fragment() {
    private lateinit var shimmerLayout: ShimmerFrameLayout
    private lateinit var recyclerView: RecyclerView
    private val apiKey = "aDBJLZnJZ6DLwIjL7wPFjJs0Y3qsvr41HkF9OIix"
   // private lateinit var searchBar: EditText
    private lateinit var titleText: TextView
   // private lateinit var adapter: MovieAdapter

   // private var fullMovieList: List<Movie> = listOf()


    private val apiService: ApiService by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie, container, false)



        shimmerLayout = view.findViewById(R.id.shimmerLayout)
        recyclerView = view.findViewById(R.id.recyclerView)
      //  searchBar = view.findViewById(R.id.searchBar)
        titleText = view.findViewById(R.id.titleText)


        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        shimmerLayout.startShimmer()


        fetchMovies()

//        searchBar.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                filterMovies(s.toString())
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//        })

        return view


    }

    private fun fetchMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val response = apiService.getMovies(apiKey)

                if (response.isSuccessful) {
                    val movies: List<Movie> = response.body()?.titles ?: listOf()

                    movies.forEach {
                        Log.d("MovieFragment", "Movie: $it")
                    }

                    withContext(Dispatchers.Main) {

                        recyclerView.adapter = MovieAdapter(movies) { movieId ->
                            val intent = Intent(requireContext(), DetailsActivity::class.java)
                            intent.putExtra("ID", movieId)
                            Log.d("MovieFragment", movieId.toString())
                            startActivity(intent)
                        }


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

//    private fun filterMovies(query: String) {
//        if (!::adapter.isInitialized) return
//        val filteredList = fullMovieList.filter {
//            it.title.contains(query, ignoreCase = true)
//        }
//        adapter.updateList(filteredList)
//    }
}



