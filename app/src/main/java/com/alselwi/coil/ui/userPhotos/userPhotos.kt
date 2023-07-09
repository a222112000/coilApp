package com.alselwi.coil.ui.userPhotos

import com.alselwi.coil.data.people.GetUserPhotos

data class userPhotos(
    val isLoading: Boolean = false,
    val photos: GetUserPhotos? = null,
    val error: String = ""
)