package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.CharacterDto
import com.example.myapplication.data.remote.dto.CharacterListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpsonsApi {
    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterDto>

    @GET("characters")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterListDto>
}
