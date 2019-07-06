package com.example.cuteweatherv1.repository.hourly

import android.widget.ImageView

/**
 * 时间：2019/7/6 09:41
 * 作者：程弋
 * 描述: 每小时天气数据封装
 */
data class Hourly (
    val hour : String,
    val weather_image : Int,
    val hour_temp : String
)