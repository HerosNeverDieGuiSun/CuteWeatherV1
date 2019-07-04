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
import androidx.lifecycle.MutableLiveData

/**
 * 时间：2019/7/4 15:36
 * 作者：程弋
 * 描述: 获取定位服务
 */
class MyLocation {
    private constructor()

    object Holder {
        val INSTANCE = MyLocation()
    }
    companion object {
        val instance: MyLocation = Holder.INSTANCE
    }

    var latitude = ""
    var longitude = ""
}