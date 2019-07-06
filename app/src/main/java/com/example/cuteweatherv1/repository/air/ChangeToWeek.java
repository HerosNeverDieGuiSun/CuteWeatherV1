package com.example.cuteweatherv1.repository.air;

import java.util.Calendar;
import java.util.Date;

public class ChangeToWeek {
    public int toWeek(){
        long time=System.currentTimeMillis();
        Date date=new Date(time);
        return parseDateToYearMonthDayWeek(date);
    }

    public int parseDateToYearMonthDayWeek(Date date){

        //获取默认选中的日期的年月日星期的值，并赋值
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期

        String yearStr = calendar.get(Calendar.YEAR)+"";//获取年份
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        String monthStr = month < 10 ? "0" + month : month + "";
        int day = calendar.get(Calendar.DATE);//获取日
        String dayStr = day < 10 ? "0" + day : day + "";
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String weekStr = "";
        /*星期日:Calendar.SUNDAY=1
         *星期一:Calendar.MONDAY=2
         *星期二:Calendar.TUESDAY=3
         *星期三:Calendar.WEDNESDAY=4
         *星期四:Calendar.THURSDAY=5
         *星期五:Calendar.FRIDAY=6
         *星期六:Calendar.SATURDAY=7 */
        switch (week) {
            case 1:
                weekStr = "周日";
                break;
            case 2:
                weekStr = "周一";
                break;
            case 3:
                weekStr = "周二";
                break;
            case 4:
                weekStr = "周三";
                break;
            case 5:
                weekStr = "周四";
                break;
            case 6:
                weekStr = "周五";
                break;
            case 7:
                weekStr = "周六";
                break;
            default:
                break;
        }
        return week;
    }
}
