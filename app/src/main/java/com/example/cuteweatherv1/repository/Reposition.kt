package com.example.cuteweatherv1.repository


class Reposition {

    object Holder {
        val INSTANCE = Reposition()
    }
    companion object {
        val instance = Holder.INSTANCE
        const val KEY = "S7Uje_wKeJ0K44HJF"
        const val GPSKEY = "755cd948c3e6001929399bff71202268"
        const val CITYKEY = "city"
        const val DAYKEY = "day"
        const val HOURKEY = "hour"
        const val AIRKEY = "air"
        const val NOTICEKEY = "notice"
        const val SUNKEY = "sun"
        const val SUGKEY = "sug"
        const val GPSURL = "http://apis.juhe.cn/"
        const val BASEURL = "https://api.seniverse.com/"
        const val AIRDAILY = "v3/air/daily.json"
        const val AIR = "v3/air/now.json?"
        const val SUNRISE = "v3/geo/sun.json"
        const val TIP = "v3/weather/alarm.json"
    }
}