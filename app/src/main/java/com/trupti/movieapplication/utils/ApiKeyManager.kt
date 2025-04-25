package com.trupti.movieapplication.utils

object ApiKeyManager {
    val API_KEY : String
        get()= System.getProperty("API_KEY") ?: "default_api_key"
}