package com.example.cuteweatherv1.repository.air.data.AirData


import com.google.gson.annotations.SerializedName

data class AirData(
    @SerializedName("results")
    val results: List<Result>
)