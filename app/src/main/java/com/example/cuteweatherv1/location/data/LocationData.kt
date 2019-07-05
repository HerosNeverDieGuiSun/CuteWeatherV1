package com.example.cuteweatherv1.location.data


import com.google.gson.annotations.SerializedName

data class LocationData(
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("result")
    val result: Result,
    @SerializedName("resultcode")
    val resultcode: String
)