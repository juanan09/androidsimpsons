package com.example.myapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("occupation") val occupation: String?,
    @SerializedName("portrait_path") val portraitPath: String?,
    @SerializedName("phrases") val phrases: List<String>?
)
