package com.example.cuteweatherv1.ui.briefly.data

import com.google.gson.annotations.SerializedName

/**
 * 时间：2019/7/4 11:14
 * 作者：程弋
 * 描述: 天气简略信息
 */

data class Location(
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("path")
    val path: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: String
)