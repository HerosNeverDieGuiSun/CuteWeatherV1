package com.example.cuteweatherv1.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cuteweatherv1.R

class FragmentDaily : Fragment() {

    companion object {
        fun newInstance() = FragmentDaily()
    }

    private lateinit var viewModel: FragmentDailyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentDailyViewModel::class.java)
        // TODO: Use the ViewModel

    }

}
