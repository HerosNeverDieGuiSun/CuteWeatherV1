package com.example.cuteweatherv1.ui.briefly.data

/**
 * 时间：2019/7/4 11:25
 * 作者：程弋
 * 描述: JSON信息生成实体类
 */
data class Now(
    val clouds: String,
    val code: String,
    val dewPoint: String,
    val feelsLike: String,
    val humidity: String,
    val pressure: String,
    val temperature: String,
    val text: String,
    val visibility: String,
    val windDirection: String,
    val windDirectionDegree: String,
    val windScale: String,
    val windSpeed: String
)