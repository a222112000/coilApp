package com.alselwi.coil.domain.user

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alselwi.coil.data.people.Photo
import com.alselwi.coil.domain.Repository

class UserDataSource(private val repositoryPhotosImpl: Repository,private val userId: String): PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val page = params.key ?: 1
            Log.d("YUY",page.toString())
            val response = repositoryPhotosImpl.getUserPhotos(userId = userId,page)
            LoadResult.Page(
                data = response.photos.photo,
                prevKey = if(page > 0) page -1 else null,
                nextKey = if(!response.photos.page.equals(0))
                    page + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}