package com.example.cuteweatherv1.repository.lifeSuggestion

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cuteweatherv1.location.MyLocation
import com.example.cuteweatherv1.repository.Reposition
import com.example.cuteweatherv1.repository.lifeSuggestion.data.LifeSuggestion
import com.example.cuteweatherv1.repository.lifeSuggestion.data.Result
import com.example.cuteweatherv1.ui.lifeSuggest.LifeService
import kotlinx.android.synthetic.main.fragment_life.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SuggestRepositoy {
    val data = ArrayList<LifeSuggestions>()
    private constructor() {

    }

    object Holder{
        val INSTANCE = SuggestRepositoy()
    }

    companion object{
        val instance = Holder.INSTANCE
    }
    val suggestionInfo = MutableLiveData<List<Result> >()
    fun getSuggestionInfo() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.seniverse.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val lifeService = retrofit.create(LifeService::class.java)
        lifeService.getInfo(Reposition.KEY, MyLocation.instance.city.value.toString())
            .enqueue(object : Callback<LifeSuggestion> {
                override fun onFailure(call: Call<LifeSuggestion>, t: Throwable) {
                    Log.e("MyLog","获取数据失败")
                }

                override fun onResponse(call: Call<LifeSuggestion>, response: Response<LifeSuggestion>) {
                    suggestionInfo.value = response.body()?.results
                }
            })
    }
}