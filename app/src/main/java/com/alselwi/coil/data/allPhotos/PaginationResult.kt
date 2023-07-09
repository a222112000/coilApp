package com.alselwi.coil.data.allPhotos

data class PaginationResult<T>(
    val data: List<T>,
    val nextPage: Int?
)
