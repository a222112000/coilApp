package com.alselwi.coil.ui.Photos

import com.alselwi.coil.data.allPhotos.Photo

data class GetPhotosState(
    val isLoading: Boolean = false,
    var photos: List<Photo>? = null,
    val error: String = ""
)