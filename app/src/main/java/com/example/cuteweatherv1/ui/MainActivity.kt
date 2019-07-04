package com.example.cuteweatherv1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.ui.city.CityMngActivity
import com.gyf.immersionbar.ImmersionBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener { item ->
            when(item?.itemId) {
                R.id.change_city -> {
                    val intent = Intent()
                    intent.setClass(applicationContext, CityMngActivity::class.java)

                    startActivity(intent)
                }
            }
            true
        }
        ImmersionBar.with(this).statusBarColor(R.color.colorPrimary).init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
