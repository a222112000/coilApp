package com.alselwi.coil.common.instance

import com.alselwi.coil.data.FlickrService
import com.alselwi.coil.data.RepositoryPhotosImpl
import com.alselwi.coil.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object instanceRetro {

val BASE_URL = "https://api.flickr.com/"

    @Singleton
    @Provides
    fun provideApiRetrofit(): Retrofit {
       return Retrofit.Builder()
           .baseUrl(BASE_URL)
           .client(OkHttpClient())
           .addConverterFactory(GsonConverterFactory.create())
           .build()
    }

    @Singleton
    @Provides
    fun flickrService(): FlickrService = provideApiRetrofit().create(FlickrService::class.java)

    @Provides
    @Singleton
    fun providePhotoRepository(api: FlickrService): Repository {
        return RepositoryPhotosImpl(api)
    }
}