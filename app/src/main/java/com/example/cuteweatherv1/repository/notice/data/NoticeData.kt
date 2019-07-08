package com.example.cuteweatherv1.repository.notice.data


import com.google.gson.annotations.SerializedName

data class NoticeData(
    @SerializedName("results")
    val results: List<Result>
)