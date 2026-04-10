# Architecture

## Design Pattern
The application will follow the **MVVM (Model-View-ViewModel)** architecture pattern, as requested by the assignment.

## Layers
* **UI (View):** `MainActivity` and XML layouts. Responsible only for displaying data and capturing user input.
* **ViewModel:** `MainViewModel`. Holds the UI state (the list of images and loading status) using `LiveData`.
* **Repository:** `ImageRepository`. Acts as the single source of truth for data. It communicates with the API service.
* **API Service:** Retrofit interface to make the actual HTTP network calls to the Unsplash API.