package com.example.cuteweatherv1.ui.briefly.data


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("last_update")
    val lastUpdate: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("now")
    val now: Now
)