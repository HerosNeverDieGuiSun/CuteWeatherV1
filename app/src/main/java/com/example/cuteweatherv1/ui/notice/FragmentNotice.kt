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


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentNotice : Fragment() {
    private val TIP_PREFIX = "this is tip No."
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var noticeJson = NoticeJson()
        var city = "beijing"
        MyLocation.instance.city.observe(this, object : Observer<String> {
            override fun onChanged(t: String) {
                city = t
                noticeJson.deal(city)
            }
        })

        tip_view.setNormalTip()
        SaveNotice.instance.description.observe(this, Observer<String>{
            tip_view.setTipList(it)
        })
//        tip_view.setTipList("asdas")


    }
    private fun generateTips(): ArrayList<String> {
        val tips = ArrayList<String>()
        for (i in 100..119) {
            tips.add(TIP_PREFIX + i)
        }
        return tips
    }


}
