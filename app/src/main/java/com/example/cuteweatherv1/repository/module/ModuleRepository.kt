package com.example.cuteweatherv1.repository.module

import com.example.cuteweatherv1.repository.module.data.Module

/**
 * Created by 胡婵娟.
 * 内容：储存模块信息
 */

class ModuleRepository {
    val data = ArrayList<Module>()

    private constructor() {
        data.add(Module("每日预报", true))
        data.add(Module("每小时天气情况", true))
        data.add(Module("实时空气质量", true))
        data.add(Module("日出日落", true))
        data.add(Module("今日建议", true))
//        data.add(Module("灾害预警", true))
    }

    object Holder{
        val INSTANCE = ModuleRepository()
    }

    companion object{
        val instance = Holder.INSTANCE
    }
}