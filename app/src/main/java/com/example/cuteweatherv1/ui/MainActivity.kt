package com.example.cuteweatherv1.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.module.DealModuleInfo
import com.example.cuteweatherv1.repository.module.ModuleRepository
import com.example.cuteweatherv1.ui.city.CityMngActivity
import com.example.cuteweatherv1.ui.air.FragmentAir
import com.example.cuteweatherv1.ui.daily.FragmentDaily
import com.example.cuteweatherv1.ui.hourly.FragmentHourly
import com.example.cuteweatherv1.ui.lifeSuggest.FragmentLife
import com.example.cuteweatherv1.ui.module.ModuleMngActivity
import com.example.cuteweatherv1.ui.notice.FragmentNotice
import com.example.cuteweatherv1.ui.sunrise.FragmentSunrise
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), AMapLocationListener {

    lateinit var sharedPreferences: SharedPreferences

    //声明AMapLocationClient类对象
    lateinit var mLocationClient : AMapLocationClient
    //声明AMapLocationClientOption对象
    lateinit var mLocationOption : AMapLocationClientOption

    var fragments = ArrayList<Fragment>()
    val fragmentDaily = FragmentDaily()
    val fragmentHourly = FragmentHourly()
    val fragmentAir = FragmentAir()
    val fragmentNotice = FragmentNotice()
    val fragmentSunrise = FragmentSunrise()
    val fragmentLife = FragmentLife()


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
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        setContentView(R.layout.activity_main)


        // 取本地功能开关状态数据
        ModuleRepository.instance.data[0].isOpen = sharedPreferences.getBoolean(Reposition.DAYKEY, true)
        Log.e("mylocal", ModuleRepository.instance.data[0].isOpen.toString())
        ModuleRepository.instance.data[1].isOpen = sharedPreferences.getBoolean(Reposition.HOURKEY, true)
        Log.e("mylocal", ModuleRepository.instance.data[1].isOpen.toString())
        ModuleRepository.instance.data[2].isOpen = sharedPreferences.getBoolean(Reposition.AIRKEY, true)
        Log.e("mylocal", ModuleRepository.instance.data[2].isOpen.toString())
        ModuleRepository.instance.data[3].isOpen = sharedPreferences.getBoolean(Reposition.NOTICEKEY, true)
        Log.e("mylocal", ModuleRepository.instance.data[3].isOpen.toString())
        ModuleRepository.instance.data[4].isOpen = sharedPreferences.getBoolean(Reposition.SUNKEY, true)
        Log.e("mylocal", ModuleRepository.instance.data[4].isOpen.toString())
        ModuleRepository.instance.data[5].isOpen = sharedPreferences.getBoolean(Reposition.SUGKEY, true)
        Log.e("mylocal", ModuleRepository.instance.data[5].isOpen.toString())

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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

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

        // 显示功能模块
        fragments.add(fragmentDaily)
        fragments.add(fragmentHourly)
        fragments.add(fragmentAir)
        fragments.add(fragmentNotice)
        fragments.add(fragmentSunrise)
        fragments.add(fragmentLife)

        val show = ArrayList<Fragment>()
        for (i in 0..ModuleRepository.instance.data.size-1) {
            if (ModuleRepository.instance.data[i].isOpen) {
                show.add(fragments[i])
            }
        }

        for (i in 0..show.size-1) {
            supportFragmentManager.beginTransaction().add(DealModuleInfo().containers[i], show.get(i)).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onResume() {
        for (i in 0..DealModuleInfo().getAllFragments().size-1) {
            when (i) {
                0 -> {
                    if (!ModuleRepository.instance.data[i].isOpen)
                        supportFragmentManager.beginTransaction().hide(fragmentDaily).commit()
                    else
                        supportFragmentManager.beginTransaction().show(fragmentDaily).commit()
                }
                1 -> {
                    if (!ModuleRepository.instance.data[i].isOpen)
                        supportFragmentManager.beginTransaction().hide(fragmentHourly).commit()
                    else
                        supportFragmentManager.beginTransaction().show(fragmentHourly).commit()
                }
                2 -> {
                    if (!ModuleRepository.instance.data[i].isOpen)
                        supportFragmentManager.beginTransaction().hide(fragmentAir).commit()
                    else
                        supportFragmentManager.beginTransaction().show(fragmentAir).commit()
                }
                3 -> {
                    if (!ModuleRepository.instance.data[i].isOpen)
                        supportFragmentManager.beginTransaction().hide(fragmentNotice).commit()
                    else
                        supportFragmentManager.beginTransaction().show(fragmentNotice).commit()
                }
                4 -> {
                    if (!ModuleRepository.instance.data[i].isOpen)
                        supportFragmentManager.beginTransaction().hide(fragmentSunrise).commit()
                    else
                        supportFragmentManager.beginTransaction().show(fragmentSunrise).commit()
                }
                5 -> {
                    if (!ModuleRepository.instance.data[i].isOpen)
                        supportFragmentManager.beginTransaction().hide(fragmentLife).commit()
                    else
                        supportFragmentManager.beginTransaction().show(fragmentLife).commit()
                }
            }
        }

        super.onResume()
    }

    override fun onDestroy() {

        val edit = sharedPreferences.edit()
        edit.putBoolean(Reposition.DAYKEY, ModuleRepository.instance.data[0].isOpen)
        edit.putBoolean(Reposition.HOURKEY, ModuleRepository.instance.data[1].isOpen)
        edit.putBoolean(Reposition.AIRKEY, ModuleRepository.instance.data[2].isOpen)
        edit.putBoolean(Reposition.NOTICEKEY, ModuleRepository.instance.data[3].isOpen)
        edit.putBoolean(Reposition.SUNKEY, ModuleRepository.instance.data[4].isOpen)
        edit.putBoolean(Reposition.SUGKEY, ModuleRepository.instance.data[5].isOpen)
        edit.commit()

        super.onDestroy()
    }
}
