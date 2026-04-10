package com.example.mip2tp2.data.repository

import com.example.mip2tp2.data.api.RetrofitClient
import com.example.mip2tp2.data.api.UnsplashApiService
import com.example.mip2tp2.data.model.UnsplashImage

/**
 * Repository class that acts as the single source of truth for image data.
 * It abstracts the network call to the Unsplash API.
 */
class ImageRepository(
    private val apiService: UnsplashApiService = RetrofitClient.apiService
) {
    /**
     * Fetches a list of random images from the API constraintly executed on a coroutine.
     */
    suspend fun getRandomImages(): List<UnsplashImage> {
        return apiService.getRandomImages()
    }
}
