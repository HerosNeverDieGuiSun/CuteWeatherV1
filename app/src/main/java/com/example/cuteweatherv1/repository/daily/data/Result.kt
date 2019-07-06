package com.example.cuteweatherv1.repository.daily.data

import com.google.gson.annotations.SerializedName

/**
 * 时间：2019/7/5 11:19
 * 作者：程弋
 * 描述: 五天内的信息
 */

data class Result(
    @SerializedName("daily")
    val daily: List<Daily>,
    @SerializedName("last_update")
    val lastUpdate: String,
    @SerializedName("location")
    val location: Location
)