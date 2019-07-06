package com.example.cuteweatherv1.repository.hourly.data


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("hourly")
    val hourly: List<Hourly>,
    @SerializedName("location")
    val location: Location
)