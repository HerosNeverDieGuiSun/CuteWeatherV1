package com.example.cuteweatherv1.ui.briefly

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.LocationService
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.ui.briefly.data.BriefInfo
import com.github.promeg.pinyinhelper.Pinyin
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 时间：2019/7/4 11:14
 * 作者：程弋
 * 描述: 天气简略信息
 */

class FragmentWeather : Fragment() {

    companion object {
        fun newInstance() = FragmentWeather()
    }

    private val repository = Reposition.instance
    private lateinit var viewModel: FragmentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentWeatherViewModel::class.java)
        viewModel.getLocation()
        viewModel.city.observe(this, Observer<String> { t ->
            //使用新数据更新界面
            Log.e("mylog", t)
        })
    }
}
