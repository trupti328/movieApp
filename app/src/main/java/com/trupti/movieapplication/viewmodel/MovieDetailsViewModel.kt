package com.trupti.movieapplication.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Snackbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trupti.movieapplication.model.Movie


import com.trupti.movieapplication.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject


class MovieDetailViewModel(private val apiService: ApiService) : ViewModel() {

    private val _movieDetail = MutableStateFlow<Movie?>(null)
    val movieDetail: StateFlow<Movie?> = _movieDetail

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun fetchMovieDetail(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getDetails(movieId, apiKey)
                if (response.isSuccessful) {
                    _movieDetail.value = response.body()
                }else {
                    _errorMessage.value = "Failed to load movie details"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}