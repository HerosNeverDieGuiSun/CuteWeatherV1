package com.example.cuteweatherv1.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.ui.city.CityMngActivity
import com.example.cuteweatherv1.repository.air.DealAriInfoJson
import com.gyf.immersionbar.ImmersionBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val dealAriInfoJson = DealAriInfoJson()
//        dealAriInfoJson.deal()

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

        getLastKnownLocation()
        Log.e("mylog", "${MyLocation.instance.latitude} ${MyLocation.instance.longitude}")
    }

    private fun getLastKnownLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = locationManager.allProviders
        for (provider in providers) {
            val location = locationManager.getLastKnownLocation(provider)
            if (location != null) {
                MyLocation.instance.latitude = location.latitude.toString()
                MyLocation.instance.longitude = location.longitude.toString()
            }
        }
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                if (location != null) {
                    MyLocation.instance.latitude = location.latitude.toString()
                    MyLocation.instance.longitude = location.longitude.toString()
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0F, locationListener)
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (location != null) {
            MyLocation.instance.latitude = location.latitude.toString()
            MyLocation.instance.longitude = location.longitude.toString()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        getLastKnownLocation()
    }
}
