package com.example.cuteweatherv1.repository.air.data.AirData


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("air")
    val air: Air,
    @SerializedName("last_update")
    val lastUpdate: String,
    @SerializedName("location")
    val location: Location
)