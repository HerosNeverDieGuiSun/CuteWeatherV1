package com.example.cuteweatherv1.location.data


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("address")
    val address: String,
    @SerializedName("business")
    val business: String,
    @SerializedName("citycode")
    val citycode: Int,
    @SerializedName("ext")
    val ext: Ext,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String,
    @SerializedName("type")
    val type: String
)