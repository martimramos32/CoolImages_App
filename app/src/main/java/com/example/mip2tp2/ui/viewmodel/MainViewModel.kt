package com.example.mip2tp2.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mip2tp2.data.model.UnsplashImage
import com.example.mip2tp2.data.repository.ImageRepository
import kotlinx.coroutines.launch

/**
 * ViewModel to manage UI-related data in a lifecycle-conscious way.
 * Exposes images and loading states using LiveData.
 */
class MainViewModel(
    private val repository: ImageRepository = ImageRepository()
) : ViewModel() {

    // LiveData for the list of random images
    private val _images = MutableLiveData<List<UnsplashImage>>()
    val images: LiveData<List<UnsplashImage>> get() = _images

    // LiveData for the loading state (true when fetching, false when done)
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        // Fetch initially when the ViewModel is created
        fetchRandomImages()
    }

    /**
     * Launches a coroutine to fetch random images from the repository and
     * updates the exposed LiveData states accordingly.
     */
    fun fetchRandomImages() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getRandomImages()
                _images.value = result
            } catch (e: Exception) {
                // In a production app, we would also expose an error LiveData.
                // For now, print the error trace.
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
