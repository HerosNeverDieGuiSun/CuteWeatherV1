package com.example.cuteweatherv1.repository.notice.data


import com.google.gson.annotations.SerializedName

data class Alarm(
    @SerializedName("description")
    val description: String,
    @SerializedName("level")
    val level: String,
    @SerializedName("pub_date")
    val pubDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
)