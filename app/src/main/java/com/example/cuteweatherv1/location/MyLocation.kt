package com.example.cuteweatherv1.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import android.content.Context.LOCATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import androidx.core.app.ActivityCompat
import android.Manifest.permission.ACCESS_FINE_LOCATION



/**
 * 时间：2019/7/4 15:36
 * 作者：程弋
 * 描述: 获取定位服务
 */
class MyLocation {
    var latitude = 0.0
    var longitude = 0.0

    @SuppressLint("MissingPermission")
    fun getLocation(context: Context): String {

        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = locationManager.allProviders
        for (provider in providers) {
            val location = locationManager.getLastKnownLocation(provider)
            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude
                return "$longitude,$latitude"
            }
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0F, locationListener)
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (location != null) {
            latitude = location.latitude
            longitude = location.longitude
        }
        return "$longitude,$latitude"
    }

    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (location != null) {
                latitude = location.latitude;
                longitude = location.longitude;
            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * @param context
     * @return true 表示开启
     */
    fun isOPen(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        val gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        val network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (gps || network) {
            return true
        }
        return false
    }

}