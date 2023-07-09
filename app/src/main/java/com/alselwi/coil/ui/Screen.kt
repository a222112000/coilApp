package com.alselwi.coil.ui

sealed class Screen(val route: String){
    object UsersPhotos: Screen("users_photos")
    object UserPhotos: Screen("user_photos")

    object PhotoDetails: Screen("photo_details")
}
