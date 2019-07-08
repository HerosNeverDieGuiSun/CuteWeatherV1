package com.example.cuteweatherv1.adapter.module

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuteweatherv1.repository.module.DealModuleInfo
import com.example.cuteweatherv1.repository.module.ModuleRepository
import com.example.cuteweatherv1.repository.module.data.Module
import kotlinx.android.synthetic.main.module_item.view.*

/**
 * Created by 胡婵娟.
 * 内容：模块管理适配器
 */
class ModuleAdapter(
    val context: Context,
    val itemLayout:Int,
    val data:ArrayList<Module>
): RecyclerView.Adapter<ModuleAdapter.ViewHolder> () {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ModuleAdapter.ViewHolder, position: Int) {
        val module = data[position]
        holder.name.text = module.name
        holder.switch_btn.isChecked = ModuleRepository.instance.data[position].isOpen
        holder.switch_btn.setOnCheckedChangeListener { view, isChecked ->
            ModuleRepository.instance.data[position].isOpen = DealModuleInfo().changeIsOpen(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleAdapter.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(itemLayout, parent, false)
        return ViewHolder(itemView)
    }

    inner class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.module_name
        val switch_btn = itemView.switch_button
    }
}