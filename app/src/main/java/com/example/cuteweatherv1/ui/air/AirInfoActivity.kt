package com.example.cuteweatherv1.ui.air

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.repository.air.AirSectionsAdapter
import com.example.cuteweatherv1.repository.air.DealAriInfoJson
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_air_info.*
import java.text.FieldPosition

class AirInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_air_info)
        val sectionsPagerAdapter =
            AirSectionsAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)

        val dealAriInfoJson = DealAriInfoJson()
        dealAriInfoJson.deal(0)

        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                val dealAriInfoJson = DealAriInfoJson()
                dealAriInfoJson.deal(position)
            }

        })
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        backiv.setOnClickListener {
            finish()
        }
//        dashboardView.setValue(700)
    }
}
