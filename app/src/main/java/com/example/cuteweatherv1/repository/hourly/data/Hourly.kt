package com.example.cuteweatherv1.repository.hourly.data


import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("code")
    val code: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("temperature")
    val temperature: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("wind_direction")
    val windDirection: String,
    @SerializedName("wind_speed")
    val windSpeed: String
)