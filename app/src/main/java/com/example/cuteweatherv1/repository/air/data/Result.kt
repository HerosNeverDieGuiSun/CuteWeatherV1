package com.example.cuteweatherv1.repository.air.data

data class Result(
    val daily: List<Daily>,
    val last_update: String,
    val location: Location
)