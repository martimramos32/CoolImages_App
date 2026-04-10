package com.example.mip2tp2.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mip2tp2.R
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.mip2tp2.data.repository.FavoritesManager
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
    private lateinit var favoritesManager: FavoritesManager
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        favoritesManager = FavoritesManager(this)
        
        imageAdapter = ImageAdapter(
            onFavoriteClick = { image ->
                val result = favoritesManager.toggleFavorite(image)
                if (!result.first && !result.second) {
                   // Tried to add more than 5
                   Toast.makeText(this, "Cannot add more than 5 favorites.", Toast.LENGTH_SHORT).show()
                }
            },
            favoritesManager = favoritesManager
        )

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

    override fun onResume() {
        super.onResume()
        // Refresh local items states just in case
        imageAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorites -> {
                startActivity(Intent(this, FavoritesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
