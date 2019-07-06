package com.example.cuteweatherv1.repository.sunrise.data


import com.google.gson.annotations.SerializedName

data class SunriseData(
    @SerializedName("results")
    val results: List<Result>
)