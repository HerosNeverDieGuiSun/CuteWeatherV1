package com.example.cuteweatherv1.repository.air.data.AirData


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("aqi")
    val aqi: String,
    @SerializedName("co")
    val co: String,
    @SerializedName("last_update")
    val lastUpdate: String,
    @SerializedName("no2")
    val no2: String,
    @SerializedName("o3")
    val o3: String,
    @SerializedName("pm10")
    val pm10: String,
    @SerializedName("pm25")
    val pm25: String,
    @SerializedName("primary_pollutant")
    val primaryPollutant: Any,
    @SerializedName("quality")
    val quality: String,
    @SerializedName("so2")
    val so2: String
)