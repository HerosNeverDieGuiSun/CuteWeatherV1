package com.example.cuteweatherv1.ui.lifeSuggest

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.lifeSuggestion.LifeSuggestions
import com.example.cuteweatherv1.repository.lifeSuggestion.SuggestRepositoy
import com.example.cuteweatherv1.repository.lifeSuggestion.data.LifeSuggestion
import com.example.cuteweatherv1.repository.lifeSuggestion.data.Result
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
        MyLocation.instance.city.observe(this, Observer<String> {
            viewModel.getInfo()
        })

        SuggestRepositoy.instance.suggestionInfo.observe(this, Observer<List<Result> > {
            val res = it?.get(0)?.suggestion
            tvCold.text = res?.chill?.brief!! ?: "无"
            tvDate.text = res?.dating?.brief!! ?: "无"
            tvFishing.text = res?.fishing?.brief!! ?: "无"
            tvHeart.text = res?.mood?.brief!! ?: "无"
            tvNight.text = res?.night_life?.brief!! ?: "无"
            tvShopping.text = res?.shopping?.brief!! ?: "无"
            tvTour.text = res?.travel?.brief!! ?: "无"
            tvTranslate.text = res?.traffic?.brief!! ?: "无"
            tvWear.text = res?.dressing?.brief!! ?: "无"
        })
        // TODO: Use the ViewModel


        suggestView.setOnClickListener {
            val intent = Intent()
            intent.setClass(activity?.applicationContext, SuggestInfoActivity::class.java)
            startActivity(intent)
        }

    }

}
