package com.example.cuteweatherv1.repository.sunrise.data


import com.google.gson.annotations.SerializedName

data class Sun(
    @SerializedName("date")
    val date: String,
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String
)