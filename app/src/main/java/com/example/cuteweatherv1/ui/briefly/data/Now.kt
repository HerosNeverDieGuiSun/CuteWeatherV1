package com.example.cuteweatherv1.ui.briefly.data


import com.google.gson.annotations.SerializedName

data class Now(
    @SerializedName("clouds")
    val clouds: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("dew_point")
    val dewPoint: String,
    @SerializedName("feels_like")
    val feelsLike: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("pressure")
    val pressure: String,
    @SerializedName("temperature")
    val temperature: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("wind_direction")
    val windDirection: String,
    @SerializedName("wind_direction_degree")
    val windDirectionDegree: String,
    @SerializedName("wind_scale")
    val windScale: String,
    @SerializedName("wind_speed")
    val windSpeed: String
)