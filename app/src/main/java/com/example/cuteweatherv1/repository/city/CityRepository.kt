package com.example.cuteweatherv1.repository.city

import com.example.cuteweatherv1.repository.city.data.CityInfo

/**
 * Created by 胡婵娟.
 * 内容：储存城市信息
 */
class CityRepository {
    val data = ArrayList<CityInfo>()

    private constructor() {
        data.add(CityInfo("南京", "", "", "", "", "", "", ""))
        data.add(CityInfo("杭州", "", "", "", "", "", "", ""))
        data.add(CityInfo("苏州", "", "", "", "", "", "", ""))
        data.add(CityInfo("桐城", "", "", "", "", "", "", ""))
        data.add(CityInfo("合肥", "", "", "", "", "", "", ""))
    }

    object Holder{
        val INSTANCE = CityRepository()
    }

    companion object{
        val instance = Holder.INSTANCE
    }
}