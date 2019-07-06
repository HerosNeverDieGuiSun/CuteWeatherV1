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
import androidx.lifecycle.Observer
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.ui.city.CityMngActivity
import com.example.cuteweatherv1.repository.sunrise.SunriseJson
import com.example.cuteweatherv1.repository.sunrise.data.SaveSunrise
import com.example.cuteweatherv1.ui.module.ModuleMngActivity
import com.example.cuteweatherv1.ui.sunrise.library.SetSunrise
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sunrise.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
//    var mSunriseView = SunriseView(applicationContext)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val dealAriInfoJson = DealAriInfoJson()
//        dealAriInfoJson.deal()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
//        toolbar.setOnMenuItemClickListener { item ->
//            when(item?.itemId) {
//                R.id.change_city -> {
//                    val intent = Intent()
//                    intent.setClass(applicationContext, CityMngActivity::class.java)
//
//                    startActivity(intent)
//                }
//            }
//            true
//        }
        change_city.setOnClickListener {
            val intent = Intent()
            intent.setClass(applicationContext, CityMngActivity::class.java)
            startActivity(intent)
        }

        module_mng.setOnClickListener {
            val intent = Intent()
            intent.setClass(applicationContext, ModuleMngActivity::class.java)
            startActivity(intent)
        }


        ImmersionBar.with(this).statusBarColor(R.color.colorPrimary).init()

        getLastKnownLocation()

        Log.e("mylog", "${MyLocation.instance.latitude} ${MyLocation.instance.longitude}")

        //mSunriseView = findViewById(R.id.sun)
       // startSunAnim(6,18)
//        val sunriseJson = SunriseJson()
//        sunriseJson.deal()
//        var sunrise:Int = 0
//        var sunset:Int = 0
//        SaveSunrise.instance.sun.observe(this,object : Observer<ArrayList<String>>{
//            override fun onChanged(t: ArrayList<String>?) {
//
//                val textSunrise = SetSunrise()
////                textSunrise.deal(findViewById(R.id.sun),6,18)
////                Log.e("my","--"+ SaveSunrise.instance.sun.value!!.get(0)+"-----"+SaveSunrise.instance.sun.value!!.get(1))
//                Tsunrise.text = SaveSunrise.instance.sun.value!!.get(0)
//                Tsunset.text = SaveSunrise.instance.sun.value!!.get(1)
//                var sunriseHour = SaveSunrise.instance.sun.value!!.get(0).substring(0,2)
//                var sunsetHour = SaveSunrise.instance.sun.value!!.get(1).substring(0,2)
//                var sunriseMin = SaveSunrise.instance.sun.value!!.get(0).substring(3,5)
//                var sunsetMin = SaveSunrise.instance.sun.value!!.get(1).substring(3,5)
//                if (sunriseMin.toInt()> 30){
//                    if (sunsetMin.toInt()>30){
//                        textSunrise.deal(findViewById(R.id.sun),sunriseHour.toInt()+1,sunsetHour.toInt()+1)
//                    } else {
//                        textSunrise.deal(findViewById(R.id.sun),sunriseHour.toInt()+1,sunsetHour.toInt())
//                    }
//                } else {
//                    if (sunsetMin.toInt()>30){
//                        textSunrise.deal(findViewById(R.id.sun),sunriseHour.toInt(),sunsetHour.toInt()+1)
//                    } else {
//                        textSunrise.deal(findViewById(R.id.sun),sunriseHour.toInt(),sunsetHour.toInt())
//                    }
//                }
//                Log.e("my","-----"+sunriseHour+"--------"+sunsetHour)
//            }
//
//        })

    }
//    private  fun startSunAnim(sunrise: Int, sunset: Int) {
//        val calendarNow = Calendar.getInstance()
//        val hour = calendarNow.get(Calendar.HOUR_OF_DAY)
//        if (hour < sunrise) {
//            mSunriseView.sunAnim(0F)
//        } else if (hour > sunset) {
//            mSunriseView.sunAnim(1F)
//        } else {
//            mSunriseView.sunAnim((hour.toFloat() - sunrise.toFloat()) / (sunset.toFloat() - sunrise.toFloat()))
//        }
//    }

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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0F, locationListener)
        if(LocationManager.NETWORK_PROVIDER == null) {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                MyLocation.instance.latitude = location.latitude.toString()
                MyLocation.instance.longitude = location.longitude.toString()
            }
        } else {
            val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (location != null) {
                MyLocation.instance.latitude = location.latitude.toString()
                MyLocation.instance.longitude = location.longitude.toString()
            }
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
