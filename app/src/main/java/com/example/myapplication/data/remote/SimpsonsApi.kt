package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.CharacterDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpsonsApi {
    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterDto>
}
