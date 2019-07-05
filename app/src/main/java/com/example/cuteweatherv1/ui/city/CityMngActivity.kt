package com.example.cuteweatherv1.ui.city

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.adapter.city.CityAdapter
import com.example.cuteweatherv1.adapter.city.CityItemClickListener
import com.example.cuteweatherv1.repository.city.CityRepository
import com.example.cuteweatherv1.repository.city.data.City
import com.example.cuteweatherv1.repository.city.CityService
import com.example.cuteweatherv1.repository.city.data.CityInfo
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
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        setContentView(R.layout.activity_city_mng)
        //添加当前定位城市
        CityRepository.instance.data.add(
            CityInfo("南京", "","","","","","","")
        )

        val saveData = sharedPreferences.getString("city", null)
        if (saveData != null){
            val city = saveData.split(",".toRegex())
            for(i in city) {
                CityRepository.instance.data.add(
                    CityInfo(i, "", "", "", "", "", "", "")
                )
            }
        }

        adapter = CityAdapter(
            applicationContext,
            R.layout.city_item,
            CityRepository.instance.data,
            object: CityItemClickListener{
                override fun onItemClick(position: Int) {
                    //点击事件

                }

                override fun onItemLongClick(position: Int): Boolean {
                    //长按时间
                    return true
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

    override fun onDestroy() {
        super.onDestroy()
        CityRepository.instance.data.clear()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

}
