package com.example.cuteweatherv1.ui.lifeSuggest

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cuteweatherv1.R
/* 开发者：--钱坤
   内容：主页面生活建议模块
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
    }

}
