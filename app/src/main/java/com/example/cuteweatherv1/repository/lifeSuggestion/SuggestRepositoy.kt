package com.example.cuteweatherv1.repository.lifeSuggestion

class SuggestRepositoy {
    val data = ArrayList<LifeSuggestions>()
    private constructor() {

    }

    object Holder{
        val INSTANCE = SuggestRepositoy()
    }

    companion object{
        val instance = Holder.INSTANCE
    }
}