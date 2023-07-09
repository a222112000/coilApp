package com.alselwi.coil.ui.userPhotos

import androidx.paging.Pager
import com.alselwi.coil.data.people.Photo

data class userPhotosState(
    val isLoading: Boolean = false,
    val photos: Pager<Int, Photo>? = null,
    val error: String = ""
)