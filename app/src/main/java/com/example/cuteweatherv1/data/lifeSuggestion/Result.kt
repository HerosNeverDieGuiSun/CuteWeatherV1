package com.example.cuteweatherv1.data.lifeSuggestion

data class Result(
    val last_update: String,
    val location: Location,
    val suggestion: Suggestion
)