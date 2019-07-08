package com.example.cuteweatherv1.ui.air

import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.air.DealAirJson
import com.example.cuteweatherv1.repository.air.data.AirData.SaveAir
import kotlinx.android.synthetic.main.fragment_air.*

/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：空气质量CardViewd操作函数
 */
class FragmentAir : Fragment() {

    companion object {
        fun newInstance() = FragmentAir()
    }
    //延迟实例化空气质量缩略图viewModel
    private lateinit var viewModel: FragmentAirViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_air, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentAirViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //空气质量Card的点击监听跳转操作
        val intent = Intent()
        aircv.setOnClickListener {
            intent.setClass(activity?.applicationContext, AirInfoActivity::class.java)
            startActivity(intent)
        }
        getApi()
    }

    //对api接口进行调用
    fun getApi(){
        //设置未选中圆环颜色
        dv1.setArcColor(Color.parseColor("#E6E6FA"))
        //设置选中后圆环颜色
        dv1.setProgressColor(Color.parseColor("#33B5E5"))
        //设置数值颜色
        dv1.setNumColor(Color.parseColor("#33B5E5"))
        //设置等级文字颜色
        dv1.setLevelColor(Color.parseColor("#33B5E5"))

        //对api进行调用
        val dealAirJson = DealAirJson()
        MyLocation.instance.city.observe(this, object : Observer<String>{
            override fun onChanged(t: String) {
                dealAirJson.deal(t)
            }
        })
        //对保存的air信息进行观察监听
        SaveAir.instance.airInfo.observe(this,object :Observer<ArrayList<String>>{
            override fun onChanged(t: ArrayList<String>) {
                //设置图表的值
                dv1.setValue(t[0].toInt(),true,true)
                //设置其他文本信息
                airpm10.text = "pm10指数："+ t[1]
                airpm25.text = "pm2.5指数："+ t[2]
                val time =t[3].substring(11,16)
                airtime.text = "更新时间："+ time
            }
        })
    }

}
