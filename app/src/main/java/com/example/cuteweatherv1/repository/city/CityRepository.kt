package com.example.cuteweatherv1.repository.city

import com.example.cuteweatherv1.repository.city.data.CityInfo

/**
 * Created by 胡婵娟.
 * 内容：储存城市信息
 */
class CityRepository {
    val data = ArrayList<CityInfo>()

    private constructor() {

    }

    object Holder{
        val INSTANCE = CityRepository()
    }

    companion object{
        val instance = Holder.INSTANCE
    }
}