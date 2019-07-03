package com.example.cuteweatherv1.ui

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cuteweatherv1.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_air.*

class FragmentAir : Fragment() {

    companion object {
        fun newInstance() = FragmentAir()
    }

    private lateinit var viewModel: FragmentAirViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("my","sad1")
        return inflater.inflate(R.layout.fragment_air, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentAirViewModel::class.java)
        Log.e("my","sad2")
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent()
        Log.e("my","sad")
        aircv.setOnClickListener {

            intent.setClass(getActivity()?.getApplicationContext(),AirInfoActivity::class.java)
            startActivity(intent)
        }

    }

}
