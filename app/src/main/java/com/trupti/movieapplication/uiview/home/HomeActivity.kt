package com.trupti.movieapplication.uiview.home


//import android.util.Log
//import androidx.activity.enableEdgeToEdge
//
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//import com.trupti.movieapplication.model.Movie
//import com.trupti.movieapplication.network.ApiService
//import com.trupti.movieapplication.network.RetrofitInstance
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import org.koin.android.ext.android.inject

import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.trupti.movieapplication.R

class HomeActivity : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private val apiKey = "aDBJLZnJZ6DLwIjL7wPFjJs0Y3qsvr41HkF9OIix"

    // Inject ApiService using Koin
//    private val apiService: ApiService by inject()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_home)
//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        fetchMovies()
//
//    }

//    private fun fetchMovies() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = RetrofitInstance.api.getMovies(apiKey)

//                val response = apiService.getMovies(apiKey)
//
//                if (response.isSuccessful) {
//                    val movies: List<Movie> = response.body()?.titles ?: listOf()
//
//                    withContext(Dispatchers.Main) {
//                        recyclerView.adapter = MovieAdapter(movies)
//                    }
//                } else {
//                    Log.e("API", "Failed: ${response.code()}")
//                }
//            } catch (e: Exception) {
//                Log.e("API", "Error: ${e.message}")
//            }
//        }
    //}


    private lateinit var toggleButton: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toggleButton = findViewById(R.id.toggleButton)

        // Default fragment
        supportFragmentManager.commit {
            replace(R.id.fragment_container, MovieFragment())
        }

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            supportFragmentManager.commit {
                replace(
                    R.id.fragment_container,
                    if (isChecked) TvShowFragment() else MovieFragment()
                )
            }
        }
    }
}

