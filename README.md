# Android Image Search and Upload App

## Developed By
- Ryan Mackintosh
- Mounir Sy

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction
MAD_Assignment2B is an Android application that allows users to search for images using the Pixabay API and display them in a RecyclerView. The application also provides functionalities to switch between linear and grid layouts for displaying images and uploading selected images to Firebase Storage.

## Features
1. **Search Images**: Users can search for images using a keyword. The application fetches images from the Pixabay API based on the search keyword and displays them in a RecyclerView.
2. **Display Images**: Load and display up to 15 images from the search results in a scrollable view.
3. **Customisable Views**: Users can switch between linear and grid layouts for displaying images with the help of a layout button.
4. **Select Image**: Users can select an image from the displayed list. The selected image can then be uploaded to Firebase Storage.
5. **Upload Image**: Users can upload the selected image to Firebase Storage. The application uses Firebase Storage to store the uploaded images.

## Key Components
1. **MainActivity**: The main activity of the application. It handles the UI interactions, search functionality, layout switching, and image uploading.
   - [View MainActivity.java](https://github.com/RJTLM/MAD_Assignment2B/blob/master/app/src/main/java/com/example/mad_assignment2b/MainActivity.java)
2. **ImageAdapter**: Adapter class for the RecyclerView. It binds the image data to the RecyclerView items.
   - [View ImageAdapter.java](https://github.com/RJTLM/MAD_Assignment2B/blob/master/app/src/main/java/com/example/mad_assignment2b/ImageAdapter.java)
3. **RemoteAPICalls**: Interface for Retrofit API calls. It defines methods for fetching search results from the Pixabay API and for fetching individual images.
   - [View RemoteAPICalls.java](https://github.com/RJTLM/MAD_Assignment2B/blob/master/app/src/main/java/com/example/mad_assignment2b/RemoteAPICalls.java)
4. **ResponseModel**: Data model for the search response from the Pixabay API.
   - [View ResponseModel.java](https://github.com/RJTLM/MAD_Assignment2B/blob/master/app/src/main/java/com/example/mad_assignment2b/ResponseModel.java)
5. **RecyclerInterface**: Interface for handling item click events in the RecyclerView.
   - [View RecyclerInterface.java](https://github.com/RJTLM/MAD_Assignment2B/blob/master/app/src/main/java/com/example/mad_assignment2b/RecyclerInterface.java)

## Layouts
1. **activity_main.xml**: The main layout of the application. It contains the search bar, layout switch button, upload button, and the RecyclerView for displaying images.
   - [View activity_main.xml](https://github.com/RJTLM/MAD_Assignment2B/blob/master/app/src/main/res/layout/activity_main.xml)
2. **image_layout.xml**: Layout for individual items in the RecyclerView. Each item displays an image.
   - [View image_layout.xml](https://github.com/RJTLM/MAD_Assignment2B/blob/master/app/src/main/res/layout/image_layout.xml)

## Technologies Used
- **Programming Language**: Java
- **Android Components**: Activities, RecyclerView, Retrofit for network calls.
- **Firebase Storage**: For uploading images.
- **Pixabay API**: For fetching images based on search queries.

## Setup
1. Clone the repository:
   ```
   git clone https://github.com/RJTLM/MAD_Assignment2B.git
   ```
2. Open the project in Android Studio.
3. Sync the project and install any required dependencies.
4. Build and run the application.

## Usage
- Use the search bar to find images.
- View the images in either a single-column or double-column layout.
- Select images to upload them to Firebase Storage.

## Contributing
Contributions to this project are welcome. Please fork the repository and submit a pull request with your changes.
