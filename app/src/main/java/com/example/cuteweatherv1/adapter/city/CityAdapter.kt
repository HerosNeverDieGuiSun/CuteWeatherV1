package com.example.cuteweatherv1.adapter.city

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.repository.city.CityService
import com.example.cuteweatherv1.repository.city.DealCityInfo
import com.example.cuteweatherv1.repository.city.data.City
import com.example.cuteweatherv1.repository.city.data.CityInfo
import kotlinx.android.synthetic.main.city_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by 胡婵娟.
 * 内容：城市管理适配器
 */
class CityAdapter(
    val context: Context,
    val itemLayout:Int,
    val data:ArrayList<CityInfo>,
    val itemClickListener: CityItemClickListener
): RecyclerView.Adapter<CityAdapter.ViewHolder> () {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = data[position]

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.seniverse.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CityService::class.java)


        service.getInfo("SAc5cXnjG7dhZBOf_", city.name)
            .enqueue(object : Callback<City> {
                override fun onFailure(call: Call<City>, t: Throwable) {
                    Log.e("MyLog", "获取数据失败")
                }

                override fun onResponse(call: Call<City>, response: Response<City>) {
                    Log.e("MyLog", "获取数据中......")

                    val cityInfo = CityInfo(
                        city.name,
                        response.body()?.results?.get(0)?.location?.path.toString(),
                        response.body()?.results?.get(0)?.now?.text.toString(),
                        response.body()?.results?.get(0)?.now?.temperature.toString(),
                        response.body()?.results?.get(0)?.now?.humidity.toString(),
                        response.body()?.results?.get(0)?.now?.wind_direction.toString(),
                        response.body()?.results?.get(0)?.now?.wind_scale.toString(),
                        response.body()?.results?.get(0)?.now?.feels_like.toString()
                    )
                    holder.city.text = cityInfo.name
                    holder.limitTemp.text = "体感温度${cityInfo.feels_like}℃"
                    holder.otherInfo.text = "湿度${cityInfo.humidity}%|${cityInfo.wind_direction}风${cityInfo.wind_scale}级"
                    holder.province.text = cityInfo.path
                    holder.temp.text = "${cityInfo.temperature}℃"
                    holder.icon.setImageResource(DealCityInfo().diaplayIcon(cityInfo.text))
                }
            })

//        holder.city.text = "苏州"
//        holder.icon.setImageResource(R.drawable.duoyun)
//        holder.limitTemp.text = "体感温度23℃"
//        holder.otherInfo.text = "湿度70%|东南风1级"
//        holder.province.text = "江苏"
//        holder.temp.text = "27℃"

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }

        holder.itemView.setOnLongClickListener{
            itemClickListener.onItemLongClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(itemLayout, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val city = itemView.city_tv
        val icon = itemView.icon
        val temp = itemView.temp_tv
        val province = itemView.province_tv
        val otherInfo = itemView.more_info
        val limitTemp = itemView.min_max_temp
    }
}