package com.example.cuteweatherv1.ui.air

import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.air.DealAirJson
import com.example.cuteweatherv1.repository.air.data.AirData.SaveAir
import com.example.cuteweatherv1.ui.air.library.DashboardView2
import kotlinx.android.synthetic.main.fragment_air.*

class FragmentAir : Fragment() {

    companion object {
        fun newInstance() = FragmentAir()
    }

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
        val intent = Intent()
        aircv.setOnClickListener {
            intent.setClass(activity?.applicationContext, AirInfoActivity::class.java)
            startActivity(intent)
        }
        dv1.setArcColor(Color.parseColor("#E6E6FA"))
        dv1.setProgressColor(Color.parseColor("#33B5E5"))
        dv1.setNumColor(Color.parseColor("#33B5E5"))
        dv1.setLevelColor(Color.parseColor("#33B5E5"))
        val dealAirJson = DealAirJson()

        MyLocation.instance.city.observe(this, object : Observer<String>{
            override fun onChanged(t: String) {
                dealAirJson.deal(t)
            }
        })
        SaveAir.instance.airNum.observe(this,object : Observer<Int> {
            override fun onChanged(t: Int) {
                dv1.setValue(t,true,true)
            }
        })
        SaveAir.instance.pm25.observe(this,object : Observer<String> {
            override fun onChanged(t: String) {
                airpm25.text = "pm2.5指数："+ t
            }
        })
        SaveAir.instance.pm10.observe(this,object : Observer<String> {
            override fun onChanged(t: String) {
                airpm10.text = "pm10指数："+ t
            }
        })
        SaveAir.instance.time.observe(this,object : Observer<String> {
            override fun onChanged(t: String) {
                val time =t.substring(11,16)
                airtime.text = "更新时间："+ time
            }
        })
    }

}
