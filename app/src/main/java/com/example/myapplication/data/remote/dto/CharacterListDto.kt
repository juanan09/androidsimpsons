package com.example.myapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterListDto(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?,
    @SerializedName("pages") val pages: Int,
    @SerializedName("results") val results: List<CharacterDto>
)
