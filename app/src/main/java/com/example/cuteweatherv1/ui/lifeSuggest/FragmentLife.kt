package com.example.cuteweatherv1.ui.lifeSuggest

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.data.lifeSuggestion.LifeSuggestion
import kotlinx.android.synthetic.main.fragment_life.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by 钱坤
 * 内容：主页面生活建议模块
 */
class FragmentLife : Fragment() {

    companion object {
        fun newInstance() = FragmentLife()
    }

    private lateinit var viewModel: FragmentLifeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_life, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentLifeViewModel::class.java)
        // TODO: Use the ViewModel
        val suggestion = viewModel.lifeService
        suggestion.getInfo("SAc5cXnjG7dhZBOf_","nanjing")
            .enqueue(object : Callback<LifeSuggestion> {
                override fun onFailure(call: Call<LifeSuggestion>, t: Throwable) {
                    Log.e("MyLog","获取数据失败")
                }

                override fun onResponse(call: Call<LifeSuggestion>, response: Response<LifeSuggestion>) {
                    tvCold.text = response.body()?.results?.get(0)?.suggestion?.chill?.brief!! ?: "无"
                    tvDate.text = response.body()?.results?.get(0)?.suggestion?.dating?.brief!! ?: "无"
                    tvFishing.text = response.body()?.results?.get(0)?.suggestion?.fishing?.brief!! ?: "无"
                    tvHeart.text = response.body()?.results?.get(0)?.suggestion?.mood?.brief!! ?: "无"
                    tvNight.text = response.body()?.results?.get(0)?.suggestion?.night_life?.brief!! ?: "无"
                    tvShopping.text = response.body()?.results?.get(0)?.suggestion?.shopping?.brief!! ?: "无"
                    tvTour.text = response.body()?.results?.get(0)?.suggestion?.travel?.brief!! ?: "无"
                    tvTranslate.text = response.body()?.results?.get(0)?.suggestion?.traffic?.brief!! ?: "无"
                    tvWear.text = response.body()?.results?.get(0)?.suggestion?.dressing?.brief!! ?: "无"
                }
            })

        suggestView.setOnClickListener {
            val intent = Intent()
            intent.setClass(activity?.applicationContext, SuggestInfoActivity::class.java)
            startActivity(intent)
        }

    }

}
