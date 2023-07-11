package com.alselwi.coil.ui

sealed class Screen(val route: String){
    object UsersPhotos: Screen("users_photos")
    object UserPhotos: Screen("user_photos")

    object PhotoDetails: Screen("photo_details")
    object SearchScreen: Screen("search_screen")
    object SettingsScreen: Screen("settings_screen")
    object PrivacyScreen: Screen("privacy_screen")
}
