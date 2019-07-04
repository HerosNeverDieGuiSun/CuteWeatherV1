package com.example.cuteweatherv1.ui.city

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.cuteweatherv1.R
import com.zaaach.citypicker.CityPicker
import com.zaaach.citypicker.adapter.OnPickListener
import com.zaaach.citypicker.model.City
import com.zaaach.citypicker.model.HotCity
import com.zaaach.citypicker.model.LocatedCity
import kotlinx.android.synthetic.main.activity_city_mng.*

class CityMngActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_mng)

        add_btn.setOnClickListener {
            val intent = Intent()
            intent.setClass(applicationContext, CityChooseActivity::class.java)

            startActivity(intent)
        }
    }
}
