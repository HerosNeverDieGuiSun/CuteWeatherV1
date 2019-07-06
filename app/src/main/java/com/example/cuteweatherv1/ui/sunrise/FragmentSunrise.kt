package com.example.cuteweatherv1.ui.sunrise


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.repository.sunrise.SunriseJson
import com.example.cuteweatherv1.repository.sunrise.data.SaveSunrise
import com.example.cuteweatherv1.ui.sunrise.library.SetSunrise


import com.example.cuteweatherv1.ui.sunrise.library.SunriseView
import kotlinx.android.synthetic.main.fragment_sunrise.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentSunrise : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sunrise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sunriseJson = SunriseJson()
        sunriseJson.deal()
        var sunrise:Int = 0
        var sunset:Int = 0
        SaveSunrise.instance.sun.observe(this,object : Observer<ArrayList<String>> {
            override fun onChanged(t: ArrayList<String>?) {

                val textSunrise = SetSunrise()
//                textSunrise.deal(findViewById(R.id.sun),6,18)
//                Log.e("my","--"+ SaveSunrise.instance.sun.value!!.get(0)+"-----"+SaveSunrise.instance.sun.value!!.get(1))
                Tsunrise.text = SaveSunrise.instance.sun.value!!.get(0)
                Tsunset.text = SaveSunrise.instance.sun.value!!.get(1)
                var sunriseHour = SaveSunrise.instance.sun.value!!.get(0).substring(0,2)
                var sunsetHour = SaveSunrise.instance.sun.value!!.get(1).substring(0,2)
                var sunriseMin = SaveSunrise.instance.sun.value!!.get(0).substring(3,5)
                var sunsetMin = SaveSunrise.instance.sun.value!!.get(1).substring(3,5)
                if (sunriseMin.toInt()> 30){
                    if (sunsetMin.toInt()>30){
                        textSunrise.deal(sun,sunriseHour.toInt()+1,sunsetHour.toInt()+1)
                    } else {
                        textSunrise.deal(sun,sunriseHour.toInt()+1,sunsetHour.toInt())
                    }
                } else {
                    if (sunsetMin.toInt()>30){
                        textSunrise.deal(sun,sunriseHour.toInt(),sunsetHour.toInt()+1)
                    } else {
                        textSunrise.deal(sun,sunriseHour.toInt(),sunsetHour.toInt())
                    }
                }
                Log.e("my","-----"+sunriseHour+"--------"+sunsetHour)
            }

        })
    }


}
