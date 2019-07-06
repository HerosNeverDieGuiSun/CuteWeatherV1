package com.example.cuteweatherv1.repository.daily.data

import com.google.gson.annotations.SerializedName

/**
 * 时间：2019/7/5 11:19
 * 作者：程弋
 * 描述: 五天内的信息
 */

data class Daily(
    @SerializedName("code_day")
    val codeDay: String,
    @SerializedName("code_night")
    val codeNight: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("high")
    val high: String,
    @SerializedName("low")
    val low: String,
    @SerializedName("precip")
    val precip: String,
    @SerializedName("text_day")
    val textDay: String,
    @SerializedName("text_night")
    val textNight: String,
    @SerializedName("wind_direction")
    val windDirection: String,
    @SerializedName("wind_direction_degree")
    val windDirectionDegree: String,
    @SerializedName("wind_scale")
    val windScale: String,
    @SerializedName("wind_speed")
    val windSpeed: String
)