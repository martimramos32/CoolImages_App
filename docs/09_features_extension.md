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