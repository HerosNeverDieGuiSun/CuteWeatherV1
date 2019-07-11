package com.example.cuteweatherv1.repository.lifeSuggestion.data

data class Result(
    val last_update: String,
    val location: Location,
    val suggestion: Suggestion
)