package com.example.cuteweatherv1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.cuteweatherv1.R
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
                    Toast.makeText(applicationContext, "更换城市", Toast.LENGTH_SHORT).show()
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
