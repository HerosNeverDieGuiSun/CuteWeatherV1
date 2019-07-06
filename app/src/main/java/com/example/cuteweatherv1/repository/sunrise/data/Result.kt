package com.example.cuteweatherv1.repository.sunrise.data


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("location")
    val location: Location,
    @SerializedName("sun")
    val sun: List<Sun>
)