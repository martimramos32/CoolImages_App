package com.example.mip2tp2.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mip2tp2.databinding.ActivityMainBinding
import com.example.mip2tp2.ui.adapter.ImageAdapter
import com.example.mip2tp2.ui.viewmodel.MainViewModel

/**
 * MainActivity - The single entry point of the application.
 * Connects the Unsplash UI Layouts, the ImageAdapter, and the MainViewModel logic.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val imageAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Toolbar as ActionBar
        setSupportActionBar(binding.toolbar)

        setupRecyclerView()
        setupSwipeRefresh()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = imageAdapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchRandomImages()
        }
    }

    private fun observeViewModel() {
        // Observe the images list to submit bounds to the adapter
        viewModel.images.observe(this) { imagesList ->
            if (imagesList != null) {
                imageAdapter.submitList(imagesList)
            }
        }

        // Observe the loading state to toggle visual indicators
        viewModel.isLoading.observe(this) { isLoading ->
            // Update SwipeRefreshLayout's native rotating indicator
            binding.swipeRefreshLayout.isRefreshing = isLoading

            // Toggle the central screen ProgressBar dynamically 
            // (Only visible initially when list is empty)
            if (isLoading && imageAdapter.itemCount == 0) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
