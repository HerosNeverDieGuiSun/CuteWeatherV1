package com.example.cuteweatherv1.repository.module

import androidx.fragment.app.Fragment
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.ui.air.FragmentAir
import com.example.cuteweatherv1.ui.daily.FragmentDaily
import com.example.cuteweatherv1.ui.hourly.FragmentHourly
import com.example.cuteweatherv1.ui.lifeSuggest.FragmentLife
import com.example.cuteweatherv1.ui.notice.FragmentNotice
import com.example.cuteweatherv1.ui.sunrise.FragmentSunrise

/**
 * Created by 胡婵娟.
 * 内容：处理模块管理页面数据
 */
class DealModuleInfo {

    val containers = arrayOf(
        R.id.container1,
        R.id.container2,
        R.id.container3,
        R.id.container4,
        R.id.container5,
        R.id.container6)

    fun changeIsOpen(position: Int): Boolean {
        var result = true
        if (ModuleRepository.instance.data[position].isOpen) {
            result = false
        } else {
            result = true
        }

        return result
    }

    fun getAllFragments():ArrayList<Fragment> {
        var fragments = ArrayList<Fragment>()
        val fragmentDaily = FragmentDaily()
        val fragmentHourly = FragmentHourly()
        val fragmentAir = FragmentAir()
        val fragmentNotice = FragmentNotice()
        val fragmentSunrise = FragmentSunrise()
        val fragmentLife = FragmentLife()
        fragments.add(fragmentDaily)
        fragments.add(fragmentHourly)
        fragments.add(fragmentAir)
        fragments.add(fragmentNotice)
        fragments.add(fragmentSunrise)
        fragments.add(fragmentLife)

        return fragments
    }
}