package com.example.cuteweatherv1.adapter.hourly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuteweatherv1.repository.hourly.Hourly
import kotlinx.android.synthetic.main.hourly_item.view.*

/**
 * 时间：2019/7/6 09:42
 * 作者：程弋
 * 描述: 每小时天气适配器
 */
class HourlyAdapter(
    private val context : Context,
    private val itemLayout : Int,
    private val data : ArrayList<Hourly>
) : RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(itemLayout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hourly = data[position]
        holder.hour.text = hourly.hour
        holder.weather_image.setImageResource(hourly.weather_image)
        holder.hour_temp.text = hourly.hour_temp
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val hour = itemView.hour
        val weather_image = itemView.weather_image
        val hour_temp = itemView.hour_temp
    }
}