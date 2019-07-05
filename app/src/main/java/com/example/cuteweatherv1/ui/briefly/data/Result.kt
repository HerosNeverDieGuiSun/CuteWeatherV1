package com.example.cuteweatherv1.ui.briefly.data


import com.google.gson.annotations.SerializedName

/**
 * 时间：2019/7/4 11:14
 * 作者：程弋
 * 描述: 天气简略信息
 */

data class Result(
    @SerializedName("last_update")
    val lastUpdate: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("now")
    val now: Now
)