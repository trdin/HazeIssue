# Haze Sample Issue Reproduction

This project is a sample Android application designed to recreate and debug an issue with the **Haze** library in Jetpack Compose. It demonstrates the use of **Haze effects** within a `Scaffold` layout that contains a `TopAppBar`, `BottomAppBar`, and a `LazyColumn` list displaying images.

## Features
- Implements **Jetpack Compose** UI with Material 3.
- Uses **LazyColumn** to display categorized images dynamically.
- Applies **Haze blur effects** to the `TopAppBar` and `BottomAppBar`.
- Uses **Coil** for asynchronous image loading.

## Purpose
The goal of this sample is to test and observe the behavior of `hazeEffect` and `hazeSource` when applied to a `LazyColumn` inside a `Scaffold`. The setup helps identify rendering issues or unexpected behavior when scrolling content beneath a blurred overlay.

## Dependencies
- Jetpack Compose Material 3
- Coil (Image loading library)
- Haze library for blur effects

## How to Run
1. Clone the repository.
2. Open the project in Android Studio.
3. Run the application on an emulator or physical device with **Android 15+** for best results.

## Observed Issue
While scrolling, the `hazeEffect` applied to the `TopAppBar` and `BottomAppBar` may exhibit unexpected rendering behaviors, such as improper blurring or flickering.

## Contact
If you encounter additional issues or have suggestions, feel free to submit an issue or contribute improvements.

