package com.example.cuteweatherv1.ui.module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.adapter.module.ModuleAdapter
import com.example.cuteweatherv1.repository.module.ModuleRepository
import kotlinx.android.synthetic.main.activity_module_mng.*

/**
 * Created by 胡婵娟.
 * 内容：模块管理页面处理
 */
class ModuleMngActivity : AppCompatActivity() {
    lateinit var adapter: ModuleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module_mng)

        adapter = ModuleAdapter(applicationContext,R.layout.module_item, ModuleRepository.instance.data)

        module_rv.adapter = adapter
        module_rv.layoutManager = LinearLayoutManager(applicationContext)

        module_back.setOnClickListener {
            finish()
        }
    }
}
