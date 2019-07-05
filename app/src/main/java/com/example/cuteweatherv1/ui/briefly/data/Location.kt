package com.example.cuteweatherv1.ui.briefly.data

/**
 * 时间：2019/7/4 11:25
 * 作者：程弋
 * 描述: JSON信息生成实体类
 */
data class Location(
    val country: String,
    val id: String,
    val name: String,
    val path: String,
    val timezone: String,
    val timezoneOffset: String
)