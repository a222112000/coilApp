package com.alselwi.coil.data

import com.alselwi.coil.data.allPhotos.GetPhotos
import com.alselwi.coil.data.people.GetUserPhotos
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FlickrService {
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=b77795cd85950532b7c0dd719019892d&format=json&nojsoncallback=1&page=0")
    suspend fun getRecentPhotos(@Query("page") page: Int): GetPhotos

    @GET("services/rest/?method=flickr.people.getPhotos&api_key=b77795cd85950532b7c0dd719019892d&user_id=128006201@N03&format=json&nojsoncallback=1&page=0")
    @Headers("Accept:application/json")
    suspend fun getPhotosByUserId(@Query("user_id") userId: String,@Query("page") page: Int): GetUserPhotos
}