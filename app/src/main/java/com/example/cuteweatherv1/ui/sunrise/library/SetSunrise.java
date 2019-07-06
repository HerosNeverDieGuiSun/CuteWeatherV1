package com.example.cuteweatherv1.ui.sunrise.library;

import android.view.View;
import com.example.cuteweatherv1.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class SetSunrise {
    private SunriseView mSunriseView;
    public void deal(View view , int sunrise , int sunset){
        mSunriseView = (SunriseView) view;
        startSunAnim(sunrise,sunset);
    }

    public void startSunAnim(int sunrise, int sunset ) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        int hour = 0;
        if(calendar.get(Calendar.HOUR_OF_DAY)+8<24){
            hour = calendar.get(Calendar.HOUR_OF_DAY)+8;
        } else {
            hour = calendar.get(Calendar.HOUR_OF_DAY)+8-24;
        }

        if(hour < sunrise){
            mSunriseView.sunAnim(0);
        }else if(hour > sunset){
            mSunriseView.sunAnim(1);
        }else {
            mSunriseView.sunAnim(((float) hour - (float) sunrise) / ((float) sunset - (float) sunrise));
        }
    }
}
