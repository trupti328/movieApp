package com.trupti.movieapplication.uiview.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import coil.compose.AsyncImage
import com.trupti.movieapplication.model.Movie


import com.trupti.movieapplication.network.ApiService
import org.koin.java.KoinJavaComponent.inject


class DetailsActivity : ComponentActivity() {

    val apiService: ApiService by inject(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {





        super.onCreate(savedInstanceState)
        val movieId = intent.getIntExtra("ID", 0)


        Log.d("MovieFragment", "Movie ID passed: $movieId")

        if (movieId==0) {
            Log.e("DetailsActivity", "Invalid movieId passed!")
            return
        }




        setContent {
            MovieDetailScreen(movieId,apiService)
        }
    }
}


@Composable
fun MovieDetailScreen(movieId: Int, apiService: ApiService) {
    var movie by remember { mutableStateOf<Movie?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(movieId) {
        try {
            Log.d("MovieDetail", "Calling API with movieId: $movieId")
            val response = apiService.getDetails(movieId)
            if (response.isSuccessful) {
                movie = response.body()

            } else {
                Log.e("MovieDetail", "Response Code: ${response.code()}")
                Log.e("MovieDetail", "Error Body: ${response.errorBody()?.string()}")
                error= "Failed to load movie details"
            }
        } catch (e: Exception) {
            Log.e("MovieDetail", "Error: ${e.message}")
            error = e.message
        } finally {
            isLoading = false
        }
    }

    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Text(
                text = "Error: $error",
                modifier = Modifier.padding(16.dp)
            )
        }

        movie != null -> {
            MovieDetailContent(movie!!)
        }
    }
}

@Composable
fun MovieDetailContent(movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = movie.poster,
            contentDescription = "Movie Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = movie.title?: "No Title",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Release Date: ${movie.release_date?: "--"}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = movie.plot_overview?: "No Description available.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

