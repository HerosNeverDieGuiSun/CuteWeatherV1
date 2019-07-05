package com.example.cuteweatherv1.repository.city

import com.example.cuteweatherv1.R

/**
 * Created by 胡婵娟.
 * 内容：对数据的处理操作
 */
class DealCityInfo {
    fun isAddRepeated(city: String): Boolean {
        var flag = false
        for (i in CityRepository.instance.data) {
            if (city == i.name) {
                flag = true
                break
            }
        }
        return flag
    }

    fun diaplayIcon(text: String): Int {
        var icon = 0
        when(text) {
            "多云" -> icon = R.drawable.duoyun
            "晴间多云" -> icon = R.drawable.duoyun
            "大部多云" -> icon = R.drawable.duoyun
            "晴" -> icon = R.drawable.qingtian
            "阴" -> icon = R.drawable.yintian
            "阵雨" -> icon = R.drawable.zhenyu
            "雷阵雨" -> icon = R.drawable.leizhenyu
            "雷阵雨伴有冰雹" -> icon = R.drawable.bingbao
            "小雨" -> icon = R.drawable.xiaoyu
            "中雨" -> icon = R.drawable.zhongyu
            "大雨" -> icon = R.drawable.dayu
            "暴雨" -> icon = R.drawable.dayu
            "大暴雨" -> icon = R.drawable.dayu
            "特大暴雨" -> icon = R.drawable.dayu
            "冻雨" -> icon = R.drawable.dayu
            "雨夹雪" -> icon = R.drawable.yujiaxue
            "阵雪" -> icon = R.drawable.zhenxue
            "小雪" -> icon = R.drawable.xiaoxue
            "中雪" -> icon = R.drawable.zhongxue
            "大雪" -> icon = R.drawable.daxue
            "暴雪" -> icon = R.drawable.daxue
            "浮尘" -> icon = R.drawable.fuchen
            "扬沙" -> icon = R.drawable.yangsha
            "沙尘暴" -> icon = R.drawable.shachenbao
            "强沙尘暴" -> icon = R.drawable.shachenbao
            "雾" -> icon = R.drawable.wu
            "霾" -> icon = R.drawable.mai
            else -> 0
        }
        return icon
    }
}