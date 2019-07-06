package com.example.cuteweatherv1.repository.hourly.data


import com.google.gson.annotations.SerializedName

data class HourlyData(
    @SerializedName("results")
    val results: List<Result>
)