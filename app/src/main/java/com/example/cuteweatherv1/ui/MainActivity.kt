package com.example.cuteweatherv1.ui

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.ui.city.CityMngActivity
import com.example.cuteweatherv1.ui.module.ModuleMngActivity
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AMapLocationListener {

    //声明AMapLocationClient类对象
    lateinit var mLocationClient : AMapLocationClient
    //声明AMapLocationClientOption对象
    lateinit var mLocationOption : AMapLocationClientOption

    override fun onLocationChanged(amapLocation: AMapLocation?) {
        if (amapLocation != null) {
            if (amapLocation.errorCode == 0) {
                MyLocation.instance.city.value = amapLocation.city.dropLast(1)
            }else {
                MyLocation.instance.city.value = "北京"
                MyLocation.instance.defaultCity.value = true
                Toast.makeText(applicationContext, "GPS未开启,请开启GPS再使用", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

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

        //初始化定位
        mLocationClient = AMapLocationClient(applicationContext)

        //设置定位回调监听
        mLocationClient.setLocationListener(this)
        mLocationOption = AMapLocationClientOption()
        mLocationOption.isOnceLocation = false
        mLocationOption.interval = 5 * 1000
        mLocationOption.locationPurpose = AMapLocationClientOption.AMapLocationPurpose.SignIn
        mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        mLocationClient.setLocationOption(mLocationOption)
        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
        mLocationClient.stopLocation()
        mLocationClient.startLocation()

        location_image.setOnClickListener {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

            if (gps) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(0,0)
            } else {
                Toast.makeText(applicationContext, "GPS未开启,请开启GPS再使用", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
