package com.example.cuteweatherv1.ui.air


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.repository.air.DealAriInfoJson
import com.example.cuteweatherv1.repository.air.data.SaveAirDaily
import com.example.cuteweatherv1.ui.air.library.DashboardView2
import kotlinx.android.synthetic.main.activity_air_info.*
import kotlinx.android.synthetic.main.fragment_air_info.*
/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：每日空气质量Fragment
 */
class FragmentAirInfo : Fragment() {
    private lateinit var airViewModel: FragmentAirViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        airViewModel = ViewModelProviders.of(this).get(FragmentAirViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_air_info, container, false)
        airViewModel.info.observe(this, Observer<ArrayList<String>> {
            dv.setValue(it[0].toInt(),true,true)
            Tso2.text = it[1]
            Tco.text = it[2]
            Tno2.text = it[3]
            To3.text = it[4]
            Tpm10.text = it[5]
            Tpm2.text = it[6]
        })
        return root
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): FragmentAirInfo {
            return FragmentAirInfo().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }


}
