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
        val textView: TextView = root.findViewById(R.id.section_label)
        val dashboardView = root.findViewById(R.id.dv) as DashboardView2
//        airViewModel.text.observe(this, Observer<String> {
//            textView.text = it
//        })
        airViewModel.airNum.observe(this, Observer<Int> {
            dashboardView.setValue(it,true,true)
        })
        airViewModel.so2num.observe(this, Observer<String> {
            Tso2.text = it
        })
        airViewModel.conum.observe(this, Observer<String> {
            Tco.text = it
        })
        airViewModel.no2num.observe(this, Observer<String> {
            Tno2.text = it
        })
        airViewModel.pm10num.observe(this, Observer<String> {
            Tpm10.text = it
        })
        airViewModel.pm25num.observe(this, Observer<String> {
            Tpm2.text = it
        })
        airViewModel.o3num.observe(this, Observer<String> {
            To3.text = it
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
