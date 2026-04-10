package com.example.mip2tp2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mip2tp2.databinding.ActivityImageDetailBinding

class ImageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar and back navigation manually
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Retrieve properties securely from Intent Bundle mapping
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        val authorName = intent.getStringExtra(EXTRA_AUTHOR_NAME)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)
        val imageId = intent.getStringExtra(EXTRA_IMAGE_ID)

        // Dynamically mount properties upon target UI fields
        binding.detailAuthorNameTextView.text = authorName
        binding.detailDescriptionTextView.text = description ?: "No description provided."
        binding.detailIdTextView.text = "ID: $imageId"

        // Display High-res imagery via Glide caching logic safely gracefully maintaining 1:1 view sizing
        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(binding.detailImageView)
    }

    companion object {
        const val EXTRA_IMAGE_URL = "extra_image_url"
        const val EXTRA_AUTHOR_NAME = "extra_author_name"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_IMAGE_ID = "extra_image_id"
    }
}
