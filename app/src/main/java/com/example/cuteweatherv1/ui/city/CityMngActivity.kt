package com.example.cuteweatherv1.ui.city

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.adapter.city.CityAdapter
import com.example.cuteweatherv1.adapter.city.CityItemClickListener
import com.example.cuteweatherv1.repository.city.CityRepository
import com.example.cuteweatherv1.repository.city.data.City
import com.example.cuteweatherv1.repository.city.CityService
import kotlinx.android.synthetic.main.activity_city_mng.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by 胡婵娟.
 * 内容：城市管理页面处理
 */
class CityMngActivity : AppCompatActivity() {
    lateinit var adapter:CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_mng)

        adapter = CityAdapter(
            applicationContext,
            R.layout.city_item,
            CityRepository.instance.data,
            object: CityItemClickListener{
                override fun onItemClick(position: Int) {

                }
            }
        )

        city_rv.adapter = adapter
        city_rv.layoutManager = LinearLayoutManager(applicationContext)

        add_btn.setOnClickListener {
            val intent = Intent()
            intent.setClass(applicationContext, CityChooseActivity::class.java)

            startActivity(intent)
        }
    }

}
