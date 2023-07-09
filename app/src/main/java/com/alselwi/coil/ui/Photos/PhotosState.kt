package com.alselwi.coil.ui.Photos

import androidx.paging.Pager
import com.alselwi.coil.data.allPhotos.Photo

data class PhotosState(
    val isLoading: Boolean = false,
    var photos: Pager<Int, Photo>? = null,
    val error: String = ""
)