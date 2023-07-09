package com.alselwi.coil.data

import com.alselwi.coil.data.allPhotos.GetPhotos
import com.alselwi.coil.data.people.GetUserPhotos
import com.alselwi.coil.domain.Repository
import javax.inject.Inject

class RepositoryPhotosImpl @Inject constructor(private val flickrService: FlickrService) :
    Repository {
    override suspend fun getPhotos(page: Int): GetPhotos {
        return flickrService.getRecentPhotos(page)
    }

    override suspend fun getUserPhotos(userId: String,page: Int): GetUserPhotos {
        return flickrService.getPhotosByUserId(userId,page)
    }
}