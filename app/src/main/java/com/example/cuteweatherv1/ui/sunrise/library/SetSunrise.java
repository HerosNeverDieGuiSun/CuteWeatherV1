package com.example.cuteweatherv1.ui.sunrise.library;

import android.view.View;
import com.example.cuteweatherv1.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：日出日落初始信息的设置函数
 */
public class SetSunrise {
    private SunriseView mSunriseView;
    public void deal(View view , int sunrise , int sunset){
        mSunriseView = (SunriseView) view;
        startSunAnim(sunrise,sunset);
    }

    public void startSunAnim(int sunrise, int sunset ) {
        //获取现在的小时，但是是没有加时区的版本，即获取到的时间是北京时间-8h，不知道为什么设置了GMT依然没用
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        //获取现在正常的时间，判断是否在第二天
        int hour = 0;
        if(calendar.get(Calendar.HOUR_OF_DAY)+8<24){
            hour = calendar.get(Calendar.HOUR_OF_DAY)+8;
        } else {
            hour = calendar.get(Calendar.HOUR_OF_DAY)+8-24;
        }
        //判断现在的时间和日出日落的关系
        if(hour < sunrise){
            mSunriseView.sunAnim(0);
        }else if(hour > sunset){
            mSunriseView.sunAnim(1);
        }else {
            mSunriseView.sunAnim(((float) hour - (float) sunrise) / ((float) sunset - (float) sunrise));
        }
    }
}
