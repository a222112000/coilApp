package com.alselwi.coil.domain.user

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.alselwi.coil.common.Resource
import com.alselwi.coil.data.people.GetUserPhotos
import com.alselwi.coil.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserPhotosUseCase @Inject constructor(private val repositoryPhotos: Repository) {

    operator fun invoke(userId: String): Flow<Resource<GetUserPhotos>> = flow {
        try {
            emit(Resource.Loading())
            val photos = repositoryPhotos.getUserPhotos(userId,0)
            Log.d("PPPP",photos.toString())
            emit(Resource.Success(photos))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: " Couldn't reach to network"))
        }
    }
}