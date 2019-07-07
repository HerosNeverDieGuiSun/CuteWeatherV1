package com.example.cuteweatherv1.ui.notice


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.notice.NoticeJson
import com.example.cuteweatherv1.repository.notice.data.SaveNotice
import kotlinx.android.synthetic.main.fragment_notice.*


/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：灾害预警信息Fragment
 */
class FragmentNotice : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notice, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var noticeJson = NoticeJson()
        //设置地理位置
        MyLocation.instance.city.observe(this, object : Observer<String> {
            override fun onChanged(t: String) {
                noticeJson.deal(t)
            }
        })
        //设置正常的提示信息，不滚动
        tip_view.setNormalTip()
        SaveNotice.instance.description.observe(this, Observer<String>{
            //设置灾害预警信息，滚动
            tip_view.setTipList(it)
        })
    }
}
