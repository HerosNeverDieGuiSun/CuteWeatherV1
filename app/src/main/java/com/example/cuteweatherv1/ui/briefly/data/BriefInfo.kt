package com.example.cuteweatherv1.ui.briefly.data


import com.google.gson.annotations.SerializedName

data class BriefInfo(
    @SerializedName("results")
    val results: List<Result>
)