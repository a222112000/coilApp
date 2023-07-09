package com.alselwi.coil.domain

import com.alselwi.coil.data.allPhotos.GetPhotos
import com.alselwi.coil.data.people.GetUserPhotos

interface Repository {
    suspend fun getPhotos(page: Int): GetPhotos
    suspend fun getUserPhotos(userId: String,page: Int): GetUserPhotos
}