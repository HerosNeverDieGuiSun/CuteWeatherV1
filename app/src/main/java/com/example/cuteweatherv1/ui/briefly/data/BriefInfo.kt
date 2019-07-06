package com.example.cuteweatherv1.ui.briefly.data


import com.google.gson.annotations.SerializedName

/**
 * 时间：2019/7/4 11:14
 * 作者：程弋
 * 描述: 天气简略信息
 */

data class BriefInfo(
    @SerializedName("results")
    val results: List<Result>
)