package com.example.cuteweatherv1.repository.city.data

/**
 * Created by 胡婵娟.
 * 内容：JSON信息生成实体类
 */
data class Result(
    val last_update: String,
    val location: Location,
    val now: Now
)