package com.trupti.movieapplication.uiview.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.trupti.movieapplication.R
import com.trupti.movieapplication.model.TvShow

import com.trupti.movieapplication.network.ApiService
import com.trupti.movieapplication.uiview.detail.DetailsActivity



import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject



class TvShowFragment : Fragment() {
    private val apiKey = "aDBJLZnJZ6DLwIjL7wPFjJs0Y3qsvr41HkF9OIix"

    private val apiService: ApiService by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    TvShowScreen(apiService, apiKey)
                }
            }
        }
    }
}

@Composable
fun TvShowScreen(apiService: ApiService, apiKey: String) {
    val tvShows = remember { mutableStateListOf<TvShow>() }
    val isLoading = remember { mutableStateOf(true) }

    val context = LocalContext.current

    LaunchedEffect(apiKey) {
        launch {
            try {
                val response = apiService.getTvShows(apiKey)
                if (response.isSuccessful) {
                    tvShows.addAll(response.body()?.titles ?: emptyList())
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                isLoading.value = false
            }
        }
    }

    if (isLoading.value) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else {

        LazyColumn(modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp)) {

            item {
                Text(
                    text = "Explore TV Shows",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(tvShows) { tvShow ->
                TvShowItem(tvShow){
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra("ID", tvShow.id)
                    context.startActivity(intent)
                }



            }
        }
    }
}

@Composable
fun TvShowItem(tvShow: TvShow,  onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = tvShow.title,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
            modifier = Modifier.padding(16.dp)
        )
    }
}