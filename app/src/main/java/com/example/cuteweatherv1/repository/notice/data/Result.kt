package com.example.cuteweatherv1.repository.notice.data


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("alarms")
    val alarms: List<Alarm>,
    @SerializedName("location")
    val location: Location
)