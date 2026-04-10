# Extension 1: Image Details Screen

## Feature Overview
When the user taps on an image in the main list, a new screen will open to display the image in a larger size, along with more details (e.g., author name, description, and the photo's unique ID).

## UI / Screens
**ImageDetailActivity**
* An `ImageView` taking up the majority of the screen to show the high-quality image.
* `TextView`s below the image to display the Author's Name, the Description (if available), and the Image ID.
* A Back button in the Toolbar to return to the main list.

## Implementation Plan (Extension 1)

**Step 8: Details Layout**
Create the XML layout `activity_image_detail.xml`. It should contain a large `ImageView` and several `TextView`s for the details. 

**Step 9: Details Activity**
Create the `ImageDetailActivity.kt`. It should retrieve the image details passed via the `Intent` extras (e.g., image URL, author name, description) and display them in the layout using the image loading library (Glide/Coil).

**Step 10: Update Adapter and Navigation**
Update the `ImageAdapter` to include an `OnClickListener` on the item. When an item is clicked, create an `Intent`, put the necessary image details as string extras, and start the `ImageDetailActivity`.

# Extension 2: Favorites System

## Feature Overview
Allows the user to save up to 5 favorite images. These favorites are persisted locally, so they remain available even after the app is restarted.

## UI / Screens
* **Main Screen & Details Screen:** Add a "Heart" icon/button to mark an image as a favorite.
* **Favorites Activity:** A new screen that displays only the list of images saved as favorites.

## Implementation Plan (Extension 2)

**Step 11: Persistence Logic**
Implement a simple storage mechanism (using SharedPreferences or a basic Room database) to save the list of favorite images (storing their IDs and URLs). Ensure the list does not exceed 5 items.

**Step 12: Favorites UI Elements**
Update the `item_image.xml` and `activity_image_detail.xml` to include a Favorite (Heart) button. The button should change its appearance (e.g., filled vs. outlined) based on whether the image is currently a favorite.

**Step 13: Favorites Screen**
Create a `FavoritesActivity` and a corresponding layout to display the saved images in a list.

**Step 14: Connect Favorite Actions**
Update the ViewModel and the Adapter to handle the click on the heart button, saving or removing the image from the favorites list in the local storage.