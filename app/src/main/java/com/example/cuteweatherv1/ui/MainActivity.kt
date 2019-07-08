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
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.WeatherApplication
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

class MainActivity : AppCompatActivity(), AMapLocationListener {

    //声明AMapLocationClient类对象
    lateinit var mLocationClient : AMapLocationClient
    //声明AMapLocationClientOption对象
    lateinit var mLocationOption : AMapLocationClientOption

    override fun onLocationChanged(amapLocation: AMapLocation?) {
        if (amapLocation != null) {
            if (amapLocation.errorCode == 0) {
                MyLocation.instance.city.value = amapLocation.city.dropLast(1)
                Log.e("mylog", "city: " + amapLocation.city)
                Log.e("mylog", "district: " + amapLocation.district)
            }else {
                MyLocation.instance.city.value = "beijing"
                MyLocation.instance.defaultCity.value = true
                Toast.makeText(applicationContext, "GPS未开启,请开启GPS再使用", Toast.LENGTH_SHORT).show()
                Log.e("mylog","location Error, ErrCode:"
                        + amapLocation.errorCode + ", errInfo:"
                        + amapLocation.errorInfo
                )
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
        mLocationOption.interval = 10 * 1000
        mLocationOption.locationPurpose = AMapLocationClientOption.AMapLocationPurpose.SignIn
        mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        mLocationClient.setLocationOption(mLocationOption)
        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
        mLocationClient.stopLocation()
        mLocationClient.startLocation()

        location_image.setOnClickListener {
            Log.e("mylog", "------------------------")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(0,0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
