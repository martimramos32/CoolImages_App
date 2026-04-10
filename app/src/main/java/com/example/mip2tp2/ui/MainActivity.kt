package com.example.mip2tp2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mip2tp2.databinding.ActivityMainBinding

/**
 * MainActivity - The single entry point of the application.
 * Displays a gallery of random Unsplash images.
 *
 * This is a skeleton created during Step 1 (Project Setup).
 * ViewModel observation, RecyclerView setup, and refresh logic
 * will be implemented in later steps.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }
}
