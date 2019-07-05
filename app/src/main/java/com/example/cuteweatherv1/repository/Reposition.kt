package com.example.cuteweatherv1.repository

class Reposition {

    object Holder {
        val INSTANCE = Reposition()
    }
    companion object {
        val instance = Holder.INSTANCE
        const val KEY = "SAc5cXnjG7dhZBOf_"
        const val GPSKEY = "755cd948c3e6001929399bff71202268"
        const val GPSURL = "http://apis.juhe.cn/"
        const val BASEURL = "https://api.seniverse.com/"
        const val AIRDAILY = "v3/air/daily.json"
    }
}