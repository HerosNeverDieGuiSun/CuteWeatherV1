package com.example.cuteweatherv1.repository.city.data

/**
 * Created by 胡婵娟.
 * 内容：JSON信息生成实体类
 */
data class Location(
    val country: String,
    val id: String,
    val name: String,
    val path: String,
    val timezone: String,
    val timezone_offset: String
)