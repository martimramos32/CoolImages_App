package com.example.mip2tp2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.content.Intent
import com.example.mip2tp2.data.model.UnsplashImage
import com.example.mip2tp2.databinding.ItemImageBinding
import com.example.mip2tp2.ui.ImageDetailActivity

/**
 * Adapter for the RecyclerView to display a list of Unsplash images.
 * Uses ViewBinding to access the views designed in item_image.xml and
 * Glide to asynchronously load the image URLs.
 */
class ImageAdapter(
    private var images: List<UnsplashImage> = emptyList()
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    /**
     * Updates the internal list of images and refreshes the UI.
     */
    fun submitList(newImages: List<UnsplashImage>) {
        images = newImages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        // Inflate the item_image.xml layout using ViewBinding
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = images[position]
        holder.bind(currentImage)
    }

    override fun getItemCount(): Int = images.size

    inner class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: UnsplashImage) {
            // Set the author's name in the TextView
            binding.authorNameTextView.text = image.user.name

            // Use Glide to load the 'regular' URL into the ImageView
            Glide.with(binding.root.context)
                .load(image.urls.regular)
                .centerCrop()
                .into(binding.photoImageView)

            // Setup click navigation (Step 10)
            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, ImageDetailActivity::class.java).apply {
                    putExtra(ImageDetailActivity.EXTRA_IMAGE_URL, image.urls.regular)
                    putExtra(ImageDetailActivity.EXTRA_AUTHOR_NAME, image.user.name)
                    putExtra(ImageDetailActivity.EXTRA_DESCRIPTION, image.description)
                    putExtra(ImageDetailActivity.EXTRA_IMAGE_ID, image.id)
                }
                context.startActivity(intent)
            }
        }
    }
}
