package com.example.cuteweatherv1.repository.air.data.AirData


import com.google.gson.annotations.SerializedName

data class Air(
    @SerializedName("city")
    val city: City,
    @SerializedName("stations")
    val stations: Any
)