package com.example.cuteweatherv1.adapter.city

/**
 * Created by 胡婵娟.
 * 内容：城市卡片点击事件监听器
 */
interface CityItemClickListener {
    fun onItemClick(position: Int)
    fun onItemLongClick(position: Int):Boolean
}