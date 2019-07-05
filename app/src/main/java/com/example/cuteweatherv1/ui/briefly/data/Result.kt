package com.example.cuteweatherv1.ui.briefly.data

/**
 * 时间：2019/7/4 11:25
 * 作者：程弋
 * 描述: JSON信息生成实体类
 */
data class Result(
    val lastUpdate: String,
    val location: Location,
    val now: Now
)