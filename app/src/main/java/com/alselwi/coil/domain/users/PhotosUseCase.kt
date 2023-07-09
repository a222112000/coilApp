package com.alselwi.coil.domain.users

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.alselwi.coil.common.Resource
import com.alselwi.coil.data.allPhotos.Photo
import com.alselwi.coil.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PhotosUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<Resource<Pager<Int, Photo>>> = flow {
        try {
            emit(Resource.Loading<Pager<Int,Photo>>())
            val photos = Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    PhotosDataSource(repository)
                }
            )
            emit(Resource.Success<Pager<Int,Photo>>(photos))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: " Couldn't reach to network"))
        }
    }
}