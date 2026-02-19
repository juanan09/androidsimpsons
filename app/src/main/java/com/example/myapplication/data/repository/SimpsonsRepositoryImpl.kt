package com.example.myapplication.data.repository

import com.example.myapplication.core.error.AppError
import com.example.myapplication.core.network.NetworkHelper
import com.example.myapplication.core.util.Resource
import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.SimpsonsApi
import com.example.myapplication.domain.model.Character
import com.example.myapplication.domain.repository.SimpsonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class SimpsonsRepositoryImpl(
    private val api: SimpsonsApi,
    private val networkHelper: NetworkHelper
) : SimpsonsRepository {

    override suspend fun getCharacter(id: Int): Resource<Character> {
        return withContext(Dispatchers.IO) {
            if (!networkHelper.isNetworkAvailable()) {
                return@withContext Resource.Error(AppError.NoInternet)
            }

            try {
                val response = api.getCharacterById(id)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Resource.Success(body.toDomain())
                    } else {
                        Resource.Error(AppError.Unknown("Empty response body"))
                    }
                } else {
                    Resource.Error(AppError.HttpError(response.code(), response.message()))
                }
            } catch (e: SocketTimeoutException) {
                Resource.Error(AppError.Timeout)
            } catch (e: Exception) {
                Resource.Error(AppError.Unknown(e.message ?: "Unknown error"))
            }
        }
    }
}
