package com.example.cuteweatherv1.repository.city.data

/**
 * Created by 胡婵娟.
 * 内容：JSON信息生成实体类
 */
data class CityInfo(
    val name:String,
    val path:String,
    val text:String,
    val temperature:String,
    val humidity:String,
    val wind_direction:String,
    val wind_scale:String,
    val feels_like:String
)