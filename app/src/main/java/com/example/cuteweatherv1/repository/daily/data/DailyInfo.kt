package com.example.cuteweatherv1.repository.daily.data

import com.google.gson.annotations.SerializedName

/**
 * 时间：2019/7/5 11:19
 * 作者：程弋
 * 描述: 五天内的信息
 */

data class DailyInfo(
    @SerializedName("results")
    val results: List<Result>
)