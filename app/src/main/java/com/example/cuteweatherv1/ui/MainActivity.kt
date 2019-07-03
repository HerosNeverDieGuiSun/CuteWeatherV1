package com.example.cuteweatherv1.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.cuteweatherv1.R
import com.readystatesoftware.systembartint.SystemBarTintManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //applyKitKatTranslucency()
    }

    /*private fun applyKitKatTranslucency() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true)
            val mTintManager = SystemBarTintManager(this)
            mTintManager.isStatusBarTintEnabled = true
            //mTintManager.setStatusBarTintResource(R.color)
        }
    }

    private fun setTranslucentStatus(state : Boolean) {
        val layoutParams = window.attributes

    }*/

}
